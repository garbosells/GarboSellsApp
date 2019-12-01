package edu.asu.garbosells.API;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import edu.asu.garbosells.Template.Category;
import edu.asu.garbosells.Template.Subcategory;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Template {
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    public List<Category> GetCategories() throws IOException {
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
            return null;
        }
    }

    public List<Subcategory> GetSubcategoriesByCategoryId(long categoryId) throws IOException {
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
            return null;
        }
    }

    public Template GetTemplateBySubcategoryId(long subcategoryId) throws IOException {
        OkHttpClient client = new OkHttpClient();
        String url = String.format("https://categorydataservice.azurewebsites.net/api/Category/GetTemplateBySubcategoryId?subcategoryId=%d", subcategoryId);
        Request request = new Request.Builder()
                .url(url)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String json = response.body().string();
            Template templates = new Gson().fromJson(json, Template.class);
            return templates;
        } catch (Exception ex) {
            System.out.println("Unable to complete operation, error: " + ex.toString());
            return null;
        }
    }
}
