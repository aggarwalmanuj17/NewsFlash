package com.manuj.projectwork;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    EditText eTxtEmail,eTxtPassword;
    Button Btnlogin,Btnsignup;

    User user;

    ProgressDialog progressDialog;

    void initViews() {
        eTxtEmail = findViewById(R.id.email);
        eTxtPassword = findViewById(R.id.password);


        Btnsignup = findViewById(R.id.signup);
        Btnlogin = findViewById(R.id.login);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait..");
        progressDialog.setCancelable(false);


        user = new User();

        Btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    user.email = eTxtEmail.getText().toString();

                    user.password = eTxtPassword.getText().toString();


           /*     String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                // String namePattern = "[a-z]";
                boolean flag=true;
                progressDialog.show();
                progressDialog.setCancelable(false);
                if (!((user.email.matches(emailPattern)) && (user.email.length() > 0))){
                    flag=false;
                    eTxtEmail.setText("");
                    eTxtEmail.setFocusable(true);
                    eTxtEmail.setError("Invalid Email");
                    progressDialog.dismiss();
                }if(!(user.password.length()>4)){
                    flag=false;
                    eTxtPassword.setText("");
                    eTxtPassword.setFocusable(true);
                    eTxtPassword.setError("Invalid Password");
                    progressDialog.dismiss();
                }
                if(flag==true){            */
              loginUserfromFirebase();

             //   }


            }
        });
        showNotification();


        Btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        initViews();
    }
    void loginUserfromFirebase() {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.signInWithEmailAndPassword(user.email, user.password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isComplete()) {
                            Toast.makeText(LoginActivity.this, "Login Success!!", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(LoginActivity.this, NewsNav.class);
                            startActivity(intent);
                            finish();

                        } else {
                            Toast.makeText(LoginActivity.this, "Something Went Wrong !!", Toast.LENGTH_LONG).show();
                        }

                    }
                });

    }void showNotification(){

        // To show the Notification
        NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Associate a NotificationChannel to NotificationManager
            NotificationChannel notificationChannel = new NotificationChannel("myId", "myChannel", NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(notificationChannel);
        }

        Intent intent = new Intent(LoginActivity.this, NewsNav.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 111, intent, 0);

        // Create Notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "myId");
        builder.setContentTitle("Welcome back to News Flash!!");
        builder.setContentIntent(pendingIntent);
        builder.setSmallIcon(R.drawable.ic_notifications_black_24dp);

        Notification notification = builder.build();

        notificationManager.notify(101, notification);

    }




}
