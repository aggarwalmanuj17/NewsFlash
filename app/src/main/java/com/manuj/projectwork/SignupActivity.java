package com.manuj.projectwork;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class SignupActivity extends AppCompatActivity {

    EditText eTxtuserName, eTxtEmail, eTxtPassword, eTxtphone;
    Button btnRegister;
    TextView txtLogin;

    User user;

    ProgressDialog progressDialog;

    void initViews() {

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait...");

        eTxtuserName = findViewById(R.id.username);
        eTxtEmail = findViewById(R.id.email);
        eTxtPassword = findViewById(R.id.password);
        eTxtphone = findViewById(R.id.mobphone);
        btnRegister = findViewById(R.id.signup);
        txtLogin = findViewById(R.id.login);

        user = new User();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user.username = eTxtuserName.getText().toString();
                user.email = eTxtEmail.getText().toString();
                user.password = eTxtPassword.getText().toString();
                user.phone = eTxtphone.getText().toString();

                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
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
                }if(!(user.password.length()>7)){
                    flag=false;
                    eTxtPassword.setText("");
                    eTxtPassword.setFocusable(true);
                    eTxtPassword.setError("Invalid Password");
                    progressDialog.dismiss();
                }
                if(!(user.username.length()>0)){
                    flag=false;
                    eTxtuserName.setText("");
                    eTxtuserName.setFocusable(true);
                    eTxtuserName.setError("Invalid UserName");
                    progressDialog.dismiss();
                }
                if(!(user.phone.length()==10 )){
                    flag=false;
                    eTxtphone.setText("");
                    eTxtphone.setFocusable(true);
                    eTxtphone.setError("Invalid Phone");
                    progressDialog.dismiss();
                }

                if(flag==true){
                    registerUserInFirebase();
                    progressDialog.show();
                }

        //        Toast.makeText(SignupActivity.this, user.toString(), Toast.LENGTH_SHORT).show();


            }
        });

        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getSupportActionBar().hide();
        initViews();
    }

    void registerUserInFirebase() {

        FirebaseAuth auth = FirebaseAuth.getInstance();
        Log.i("User Data =>",""+user.email+"=>"+user.password);
        auth.createUserWithEmailAndPassword(user.email, user.password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isComplete()) {
                            saveUserInFirebase();
                        } else {
                            Toast.makeText(SignupActivity.this, "Something Went Wrong !!", Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }

    void saveUserInFirebase() {

        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        String uid = firebaseUser.getUid(); // This is uid of User which we have just created

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("users").document(uid).set(user)
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isComplete()) {
                            progressDialog.dismiss();
                            Intent intent = new Intent(SignupActivity.this, NewsNav.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(SignupActivity.this, "Something Went Wrong !!", Toast.LENGTH_LONG).show();
                        }
                        showNotification();
                    }
                });
    }
    void showNotification(){

        // To show the Notification
        NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Associate a NotificationChannel to NotificationManager
            NotificationChannel notificationChannel = new NotificationChannel("myId", "myChannel", NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(notificationChannel);
        }

        Intent intent = new Intent(SignupActivity.this, NewsNav.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 111, intent, 0);

        // Create Notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "myId");
        builder.setContentTitle("Welcome to News Flash!!");
        builder.setContentText("Get Ready for the Updates around the World  "+user.username);
        builder.setContentIntent(pendingIntent);
        builder.setSmallIcon(R.drawable.ic_notifications_black_24dp);

        Notification notification = builder.build();

        notificationManager.notify(101, notification);

    }
}
