package edu.asu.garbosells.API.Remote;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;

import com.google.gson.Gson;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import edu.asu.garbosells.API.Interfaces.IThirdPartyListingAPI;
import edu.asu.garbosells.Item.PostListingRequest;
import edu.asu.garbosells.R;
import edu.asu.garbosells.Template.Template;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RemotePostListingAPI implements IThirdPartyListingAPI {
    private Context context;
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    public RemotePostListingAPI(Context context) {
        this.context = context;
    }

    public class PostListingTask extends AsyncTask<PostListingRequest, Void, String> {
        AlertDialog.Builder alertDialogBuilder;
        PostListingRequest request;

        @Override
        protected void onPreExecute() {
            alertDialogBuilder = new AlertDialog.Builder(context);

        }
        @Override
        protected String doInBackground(PostListingRequest... postListingRequests) {
            this.request = postListingRequests[0];
            return PostListingAsync(request);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            alertDialogBuilder.setTitle("Post Listing Request Result");
            alertDialogBuilder.setMessage(result);
            alertDialogBuilder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
//                    Intent intent = new Intent(context, ListActivity.class);
//                    context.startActivity(intent);
                }
            });
            alertDialogBuilder.create().show();
        }
    }

    private String PostListingAsync(PostListingRequest postListingRequest) {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();
        String url = "https://listingservice.azurewebsites.net/api/Listing/PostListing";
        RequestBody body = RequestBody.create(JSON, new Gson().toJson(postListingRequest));
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Connection","close")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String json = response.body().string();
            return json;
        } catch (Exception ex) {
            return "Unable to complete operation, error: " + ex.toString();
        }
    }

    @Override
    public void PostListing(PostListingRequest request) {
        try {
            request.listing.inventoryItem.price = "5.00";
            new PostListingTask().execute(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
