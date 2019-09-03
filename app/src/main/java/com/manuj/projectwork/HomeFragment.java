package com.manuj.projectwork;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    ListView simpleList;
    View myview;
    SearchView mSearch;
    ArrayList<country> arrycount;
    int flags[] = {R.drawable.ic_argentina, R.drawable.ic_australia, R.drawable.ic_austria, R.drawable.ic_belgium, R.drawable.ic_brazil, R.drawable.ic_canada,R.drawable.china, R.drawable.cuba, R.drawable.egypt, R.drawable.france, R.drawable.greece, R.drawable.hungary, R.drawable.india, R.drawable.indonesia, R.drawable.japan, R.drawable.latvia, R.drawable.malaysia, R.drawable.mexico, R.drawable.newzealand, R.drawable.norway, R.drawable.poland, R.drawable.russia, R.drawable.singapore, R.drawable.switzerland, R.drawable.turkey, R.drawable.united_kingdom, R.drawable.united_states};


    public HomeFragment() {
    }




    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
       arrycount =new ArrayList<country>();
        final country cnt=new country("Argentina",R.drawable.ic_argentina);
        arrycount.add(cnt);
        final country cnt1=new country("Australia",R.drawable.ic_australia);
        arrycount.add(cnt1);
        final country cnt2=new country("Austria",R.drawable.ic_austria);
        arrycount.add(cnt2);
        final country cnt3=new country("Belgium",R.drawable.ic_belgium);
        arrycount.add(cnt3);
         final country cnt4=new country("Brazil",R.drawable.ic_brazil);
        arrycount.add(cnt4);
        final country cnt5=new country("Canada",R.drawable.ic_canada);
        arrycount.add(cnt5);
        final country cnt6=new country("China",R.drawable.china);
        arrycount.add(cnt6);
        final country cnt7=new country("Cuba",R.drawable.cuba);
        arrycount.add(cnt7);
        final country cnt8=new country("Egypt",R.drawable.egypt);
        arrycount.add(cnt8);
        final country cnt9=new country("France",R.drawable.france);
        arrycount.add(cnt9);
        final country cnt10=new country("Greece",R.drawable.greece);
        arrycount.add(cnt10);
        final country cnt11=new country("Hungary",R.drawable.hungary);
        arrycount.add(cnt11);
        final country cnt12=new country("India",R.drawable.india);
        arrycount.add(cnt12);
        final country cnt25=new country("Indonesia",R.drawable.indonesia);
        arrycount.add(cnt25);
        final country cnt13=new country("Japan",R.drawable.japan);
        arrycount.add(cnt13);
        final country cnt14=new country("Latvia",R.drawable.latvia);
        arrycount.add(cnt14);
        final country cnt15=new country("Malaysia",R.drawable.malaysia);
        arrycount.add(cnt15);
        final country cnt16=new country("Mexico",R.drawable.mexico);
        arrycount.add(cnt16);
        final country cnt17=new country("New Zealand",R.drawable.newzealand);
        arrycount.add(cnt17);
        final country cnt18=new country("Norway",R.drawable.norway);
        arrycount.add(cnt18);
        final country cnt19=new country("Poland",R.drawable.poland);
        arrycount.add(cnt19);
        final country cnt26=new country("Russia",R.drawable.russia);
        arrycount.add(cnt26);
        final country cnt20=new country("Singapore",R.drawable.singapore);
        arrycount.add(cnt20);
        final country cnt21=new country("Switzerland",R.drawable.switzerland);
        arrycount.add(cnt21);
        final country cnt22=new country("Turkey",R.drawable.turkey);
        arrycount.add(cnt22);
        final country cnt23=new country("United Kingdom",R.drawable.united_kingdom);
        arrycount.add(cnt23);
        final country cnt24=new country("United States",R.drawable.united_states);
        arrycount.add(cnt24);








        myview = inflater.inflate(R.layout.fragment_home, container, false);
        mSearch=(SearchView)myview.findViewById(R.id.SearchView1);
        simpleList = (ListView) myview.findViewById(R.id.simpleListView);
        final ListAdapter listAdapter = new ListAdapter(getContext(),R.layout.activity_list_view_country,arrycount );
        simpleList.setAdapter(listAdapter);

        Animation animation1 = AnimationUtils.loadAnimation(getContext(),
                R.anim.left_to_right);
        myview.startAnimation(animation1);


        simpleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                country c = listAdapter.getItem(i);
                String url = "";
                if (c.equals(cnt)) {
                    url = "https://newsapi.org/v2/top-headlines?country=ar&apiKey=a9847701d89c45f599a897679342563c";
                } else if(c.equals(cnt1)){
                    url = "https://newsapi.org/v2/top-headlines?country=au&apiKey=a9847701d89c45f599a897679342563c";
            }else if(c.equals(cnt2)){
                    url = "https://newsapi.org/v2/top-headlines?country=at&apiKey=a9847701d89c45f599a897679342563c";
                }else if(c.equals(cnt3)){
                    url = "https://newsapi.org/v2/top-headlines?country=be&apiKey=a9847701d89c45f599a897679342563c";
                }else if(c.equals(cnt4)){
                    url = "https://newsapi.org/v2/top-headlines?country=br&apiKey=a9847701d89c45f599a897679342563c";
                }else if(c.equals(cnt5)){
                    url = "https://newsapi.org/v2/top-headlines?country=ca&apiKey=a9847701d89c45f599a897679342563c";
                }else if(c.equals(cnt6)){
                    url = "https://newsapi.org/v2/top-headlines?country=cn&apiKey=a9847701d89c45f599a897679342563c";
                }else if(c.equals(cnt7)){
                    url = "https://newsapi.org/v2/top-headlines?country=cu&apiKey=a9847701d89c45f599a897679342563c";
                }else if(c.equals(cnt8)){
                    url = "https://newsapi.org/v2/top-headlines?country=eg&apiKey=a9847701d89c45f599a897679342563c";
                }else if(c.equals(cnt9)){
                    url = "https://newsapi.org/v2/top-headlines?country=fr&apiKey=a9847701d89c45f599a897679342563c";
                }else if(c.equals(cnt10)){
                    url = "https://newsapi.org/v2/top-headlines?country=gr&apiKey=a9847701d89c45f599a897679342563c";
                }else if(c.equals(cnt11)){
                    url = "https://newsapi.org/v2/top-headlines?country=hu&apiKey=a9847701d89c45f599a897679342563c";
                }else if(c.equals(cnt12)){
                    url = "https://newsapi.org/v2/top-headlines?country=in&apiKey=a9847701d89c45f599a897679342563c";
                }else if(c.equals(cnt25)){
                    url = "https://newsapi.org/v2/top-headlines?country=id&apiKey=a9847701d89c45f599a897679342563c";
                }else if(c.equals(cnt13)){
                    url = "https://newsapi.org/v2/top-headlines?country=jp&apiKey=a9847701d89c45f599a897679342563c";
                }else if(c.equals(cnt14)){
                    url = "https://newsapi.org/v2/top-headlines?country=lv&apiKey=a9847701d89c45f599a897679342563c";
                }else if(c.equals(cnt15)){
                    url = "https://newsapi.org/v2/top-headlines?country=my&apiKey=a9847701d89c45f599a897679342563c";
                }else if(c.equals(cnt16)){
                    url = "https://newsapi.org/v2/top-headlines?country=mx&apiKey=a9847701d89c45f599a897679342563c";
                }else if(c.equals(cnt17)){
                    url = "https://newsapi.org/v2/top-headlines?country=nz&apiKey=a9847701d89c45f599a897679342563c";
                }else if(c.equals(cnt18)){
                    url = "https://newsapi.org/v2/top-headlines?country=no&apiKey=a9847701d89c45f599a897679342563c";
                }else if(c.equals(cnt19)){
                    url = "https://newsapi.org/v2/top-headlines?country=pl&apiKey=a9847701d89c45f599a897679342563c";
                }else if(c.equals(cnt20)){
                    url = "https://newsapi.org/v2/top-headlines?country=ru&apiKey=a9847701d89c45f599a897679342563c";
                }else if(c.equals(cnt26)){
                    url = "https://newsapi.org/v2/top-headlines?country=sg&apiKey=a9847701d89c45f599a897679342563c";
                }else if(c.equals(cnt21)){
                    url = "https://newsapi.org/v2/top-headlines?country=ch&apiKey=a9847701d89c45f599a897679342563c";
                }else if(c.equals(cnt22)){
                    url = "https://newsapi.org/v2/top-headlines?country=tr&apiKey=a9847701d89c45f599a897679342563c";
                }else if(c.equals(cnt23)){
                    url = "https://newsapi.org/v2/top-headlines?country=gb&apiKey=a9847701d89c45f599a897679342563c";
                }else if(c.equals(cnt24)){
                    url = "https://newsapi.org/v2/top-headlines?country=us&apiKey=a9847701d89c45f599a897679342563c";
                }
                Intent intent=new Intent(getContext(),NewsViewActivity.class);
                intent.putExtra("keyUrl",url);
                startActivity(intent);
            }

        });

         mSearch.setActivated(true);
        mSearch.setQueryHint("Discover Countries");
        mSearch.onActionViewExpanded();
        mSearch.setIconified(false);
        mSearch.clearFocus();
        mSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String txt) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String txt) {

                listAdapter.getFilter().filter(txt);
                return true;
            }
        });




        return myview;
    }



}




