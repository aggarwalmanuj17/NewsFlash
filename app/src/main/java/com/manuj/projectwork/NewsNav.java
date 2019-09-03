package com.manuj.projectwork;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.Menu;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class NewsNav extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    User user;
    TextView Name,Email;







    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment=null;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    selectedFragment=null;
                    selectedFragment=new HomeFragment();
                    getSupportActionBar().setTitle("Home");
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).addToBackStack(null).commit();

                    break;
                case R.id.navigation_allnews:
                  Intent intent=new Intent(NewsNav.this,NewsViewActivity.class);
                startActivity(intent);

                 break;

                case R.id.navigation_profile:
                    selectedFragment=null;
                    selectedFragment=new ProfileFragment();
                    getSupportActionBar().setTitle("Profile");

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).addToBackStack(null).commit();

        break;

            }


            return true;
        }
    };



    void fetchProfile(){
        NavigationView navigationView = findViewById(R.id.nav_view);
        View hView = navigationView.getHeaderView(0);
        Name = (TextView)hView.findViewById(R.id.textName);
        Email = (TextView)hView.findViewById(R.id.textEmail);
        user=new User();

        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        String uid = firebaseUser.getUid();

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("users").document(uid)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            user=document.toObject(User.class);
                            Log.i("User => ",""+user);
                            Name.setText(user.username);
                            Email.setText(user.email);
                        } else {
                            Log.i("Username", "Error getting documents.", task.getException());
                        }
                    }
                });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_nav);



        fetchProfile();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        BottomNavigationView navView = findViewById(R.id.bottom_nav_view);



        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navView.setSelectedItemId(R.id.navigation_home);

    }



        @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.news_nav, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Fragment newfragment;
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            newfragment=new CricketView();
            getSupportActionBar().setTitle("Cricket Score");
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,newfragment).commit();


        } else if (id == R.id.nav_gallery) {
            newfragment=new WeatherView();
            getSupportActionBar().setTitle("Weather Forecast");
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,newfragment).commit();

        } else if (id == R.id.nav_slideshow) {
            newfragment=new StockView();
            getSupportActionBar().setTitle("Stock Market");
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,newfragment).commit();

        } else if (id == R.id.nav_tools) {

        } else if (id == R.id.nav_share) {

            Intent intent=new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            String shareBody="I suggest this app for you : https://play.google.com/store/apps/details?id=com.android.chrome";
            String shareSub="News Flash";
            intent.putExtra(Intent.EXTRA_SUBJECT,shareSub);
            intent.putExtra(Intent.EXTRA_TEXT,shareBody);
            startActivity(Intent.createChooser(intent,"Share using"));


        } else if (id == R.id.nav_send) {

            Intent Email = new Intent(Intent.ACTION_SEND);
            Email.setType("text/email");
            Email.putExtra(Intent.EXTRA_EMAIL, new String[] { "aggarwalmanuj176@gmail.com" });
            Email.putExtra(Intent.EXTRA_SUBJECT, "Feedback");
            Email.putExtra(Intent.EXTRA_TEXT, "Dear ...," + "");
            startActivity(Intent.createChooser(Email, "Send Feedback:"));
            return true;

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
