package edu.asu.garbosells.API.Remote;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import edu.asu.garbosells.API.Interfaces.ITemplateAPI;
import edu.asu.garbosells.Template.Category;
import edu.asu.garbosells.Template.Subcategory;
import edu.asu.garbosells.Template.Template;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RemoteTemplateAPI implements ITemplateAPI {
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    public class GetCategoriesTask extends AsyncTask<String, Void, List<Category>> {

        @Override
        protected List<Category> doInBackground(String... strings) {
            return GetCategoriesAsync();
        }
    }

    public class GetSubcategoriesTask extends AsyncTask<Long, Void, List<Subcategory>> {
        @Override
        protected List<Subcategory> doInBackground(Long... longs) {
            return GetSubcategoriesByCategoryIdAsync(longs[0]);
        }
    }

    public List<Category> GetCategories() {
        try {
            return new GetCategoriesTask().execute("go").get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public List<Subcategory> GetSubcategoriesByCategoryId(long categoryId) {
        try {
            return new GetSubcategoriesTask().execute(categoryId).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    private List<Category> GetCategoriesAsync() {
        OkHttpClient client = new OkHttpClient();
        String url = "https://categorydataservice.azurewebsites.net/api/Category/GetAllCategories";
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            String json = response.body().string();
            Type CategoryListType = new TypeToken<List<Category>>(){}.getType();
            List<Category> categories = new Gson().fromJson(json, CategoryListType);
            return categories;
        } catch (Exception ex) {
            System.out.println("Unable to complete operation, error: " + ex.toString());
            return new ArrayList<>();
        }
    }

    private List<Subcategory> GetSubcategoriesByCategoryIdAsync(long categoryId) {
        OkHttpClient client = new OkHttpClient();
        String url = String.format("https://categorydataservice.azurewebsites.net/api/Category/GetSubcategoriesByCategoryId?categoryId=%d", categoryId);
        Request request = new Request.Builder()
                .url(url)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String json = response.body().string();
            Type SubcategoryListType = new TypeToken<List<Subcategory>>(){}.getType();
            List<Subcategory> subcategories = new Gson().fromJson(json, SubcategoryListType);

            return subcategories;
        } catch (Exception ex) {
            System.out.println("Unable to complete operation, error: " + ex.toString());
            return new ArrayList<>();
        }
    }

    public Template GetTemplateBySubcategoryId(long subcategoryId) {
        OkHttpClient client = new OkHttpClient();
        String url = String.format("https://categorydataservice.azurewebsites.net/api/Category/GetTemplateBySubcategoryId?subcategoryId=%d", subcategoryId);
        Request request = new Request.Builder()
                .url(url)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String json = response.body().string();
            Template template = new Gson().fromJson(json, Template.class);
            return template;
        } catch (Exception ex) {
            System.out.println("Unable to complete operation, error: " + ex.toString());
            return null;
        }
    }
}
