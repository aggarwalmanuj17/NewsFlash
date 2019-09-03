package com.manuj.projectwork;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class NewsViewActivity extends AppCompatActivity  implements AdapterView.OnItemClickListener{

    NewsAdapter adapter;
    ArrayList<NewsFlash> newsList;
    StringBuilder builder;

    ListView listView;
    ProgressDialog progressDialog;

    fetchNews task;
    String urlmain;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_view);

        getSupportActionBar().hide();

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Please wait...");

        task=new fetchNews();
        task.execute();

         listView=findViewById(R.id.list1);
        Intent rcv = getIntent();
        urlmain=rcv.getStringExtra("keyUrl");
  Intent intent=new Intent(NewsViewActivity.this,DetailedNews.class);
        intent.putExtra("keyUrl", urlmain);
//        startActivity(intent);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        NewsFlash flash=newsList.get(i);
        Intent intent=new Intent(NewsViewActivity.this,DetailedNews.class);
      intent.putExtra("keyUrl",flash.url);
//        overridePendingTransition(R.anim.news_view, R.anim.news_view);

        startActivity(intent);


    }

    class fetchNews  extends AsyncTask {


        protected Object doInBackground(Object[] objects) {
try{
    URL url;
    if(urlmain!=null) {
        url = new URL(urlmain);
    }else {
        url=new URL("https://newsapi.org/v2/top-headlines?sources=techcrunch&apiKey=a9847701d89c45f599a897679342563c");
    }

            URLConnection connection =url.openConnection();

            InputStream inputStream= connection.getInputStream();

            BufferedReader reader =new BufferedReader(new InputStreamReader(inputStream));

            String line="";

            builder =new StringBuilder();

            // Add Response line by line in StringBuilder
            while ((line= reader.readLine()) !=null){
                builder.append(line);
            }

            // We now need to display the data in Toast or Log


        } catch (Exception e) {
            e.printStackTrace();
        }
            return null;
        }

        @Override
        protected void onPreExecute() {

            progressDialog.show();
        }

        @Override
        protected void onPostExecute(Object o) {

            Log.i("NewsViewActivity", builder.toString());

            parseJSONResponse();
        }

    }



    void parseJSONResponse() {

        try {

            // Create JSON Object from JSON Response
            JSONObject jObj = new JSONObject(builder.toString());
            JSONArray array = jObj.getJSONArray("articles");

            newsList = new ArrayList<>();

            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);



                // 3. Meaningful data from JSON Data is represented as an Object in Java
                NewsFlash flash = new NewsFlash();

                flash.author = obj.getString("author");
                flash.title = obj.getString("title");
                flash.url = obj.getString("url");
                flash.urlToImage = obj.getString("urlToImage");
                flash.publishedAt = obj.getString("publishedAt");


                newsList.add(flash);

                Log.i("TechCrunchNewsActivity", flash.toString());
            }

            adapter = new NewsAdapter(NewsViewActivity.this, R.layout.content_news, newsList);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(NewsViewActivity.this);
            progressDialog.dismiss();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}





