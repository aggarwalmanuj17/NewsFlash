package com.manuj.projectwork;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NewsAdapter extends ArrayAdapter<NewsFlash> {

    Context context;
    int resource;
    ArrayList<NewsFlash> objects;

    public NewsAdapter(Context context, int resource, ArrayList<NewsFlash> objects) {
        super(context, resource, objects);

        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }
    // getView will execute n number of times from 0 to n-1 automatically
    // where n is size of objects ArrayList
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        View view = LayoutInflater.from(context).inflate(resource, parent, false);
        ImageView image = view.findViewById(R.id.imageView);
        TextView txtTitle = view.findViewById(R.id.textViewTitle);
        TextView txtAuthor = view.findViewById(R.id.textViewAuthor);
        TextView txtPublishedAt = view.findViewById(R.id.textViewPublishedAt);
        //2. Fetch the Object from ArrayList
        NewsFlash news = objects.get(position);

        //3. Set Data on View


        Picasso.get().load(news.urlToImage).into(image);

        txtTitle.setText(news.title);
        txtAuthor.setText(news.author);
        txtPublishedAt.setText(news.publishedAt);

        //4. Return the View
        return view;
    }
}


