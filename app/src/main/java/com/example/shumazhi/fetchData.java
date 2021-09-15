package com.example.shumazhi;

import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static android.widget.Toast.LENGTH_SHORT;

public class fetchData extends AsyncTask<Void,Void,Void> {

    static String dataUrl;
    String data = "";
    String name,model,description,height,width,price,rating;
    String imgUrl;

    public static void data(String urlData)
    {
        dataUrl = urlData;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            URL url = new URL(dataUrl);

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";

            while(line != null)
            {
              line = bufferedReader.readLine();
               data = data + line;
            }

                JSONObject jo = new JSONObject(data);
                name = jo.getString("name");

                model = jo.getString("model");
                description = jo.getString("description");
                height = jo.getString("height");
                width = jo.getString("width");
                price = jo.getString("price");
                rating = jo.getString("rating");
                imgUrl = jo.getString("image");


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        ProductDetailActivity.name.setText(this.name);

        Picasso.get().load(imgUrl).placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(ProductDetailActivity.imgView,new com.squareup.picasso.Callback()
                {

                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError(Exception e) {

                    }
                });


        ProductDetailActivity.description.setText(this.description);
        ProductDetailActivity.model.setText(this.model);
        ProductDetailActivity.height.setText(this.height);
        ProductDetailActivity.width.setText(this.width);
        ProductDetailActivity.price.setText(this.price);
        ProductDetailActivity.rating.setText(this.rating);



    }
}
