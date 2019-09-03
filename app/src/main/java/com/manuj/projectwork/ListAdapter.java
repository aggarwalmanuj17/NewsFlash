package com.manuj.projectwork;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.DataBindingUtil;


import com.manuj.projectwork.databinding.RowItemBinding;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends BaseAdapter implements Filterable {

    Context context;
    Filter FilterRecords;
    int resource;
    ArrayList<country> objects;
    ArrayList<country> objectsfull;
  //  int flags[];
    private   LayoutInflater inflater;

    public ListAdapter(Context context, int resource, ArrayList<country> objects ) {
    this.context = context;
    this.resource=resource;
    this.objects=objects;


        objectsfull=new ArrayList<>(objects);

//        inflater = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public country getItem(int i) {
        return objects.get(i);

    }

    @Override
    public long getItemId(int i) {
        return i;
    }


    @Override
    public View getView(int i, View view,final  ViewGroup parent) {
     // view = inflater.inflate(resource, null);
         view = LayoutInflater.from(context).inflate(resource, parent, false);
        country cnt=objects.get(i);

      TextView cntry = (TextView) view.findViewById(R.id.textView);
       ImageView icon = (ImageView) view.findViewById(R.id.icon);
       String c=cnt.countryname;
        cntry.setText(c);
        icon.setImageResource(cnt.flags);
        return view;


    }

    public Filter getFilter() {
        if(FilterRecords==null){
            FilterRecords=new RecordFilter();
        }
        return FilterRecords;
    }

    private class RecordFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            FilterResults results = new FilterResults();
            ArrayList<country> fRecords = new ArrayList<>();
            if (charSequence == null || charSequence.length() == 0) {
                fRecords.addAll(objectsfull);

            } else {
                //Need Filter
                // it matches the text  entered in the edittext and set the data in adapter list


                for (country s : objectsfull) {
                    if (s.countryname.toUpperCase().trim().contains(charSequence.toString().toUpperCase().trim())) {
                        fRecords.add(s);
                    }
                }

            }
            results.values = fRecords;
            results.count = fRecords.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            objects.clear();
            objects = (ArrayList<country>) filterResults.values;
            notifyDataSetChanged();
        }
    }
    }

