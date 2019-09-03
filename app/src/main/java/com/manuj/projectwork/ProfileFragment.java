package com.manuj.projectwork;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment  {
    TextView eTxtEmail, eTxtUsername, eTxtMobile, eTxtPassword;
    Button btnlogout;
    FirebaseAuth auth;
    User user;
    FirebaseAuth.AuthStateListener authListener;
    DatabaseReference mDatabase;
    View view;
   ProgressDialog progressDialog;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_profile, container, false);



        eTxtEmail = (TextView)view.findViewById(R.id.profile_Email);
        eTxtUsername = (TextView) view.findViewById(R.id.profile_User);
        eTxtMobile = (TextView) view.findViewById(R.id.profile_mobile);
        eTxtPassword= (TextView) view.findViewById(R.id.profile_pass);
        btnlogout=(Button)view.findViewById(R.id.profile_Logout);

        Animation animation1 = AnimationUtils.loadAnimation(getContext(),
                R.anim.left_to_right);
        view.startAnimation(animation1);




        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth auth=FirebaseAuth.getInstance();
                 auth.signOut();
                Intent intent=new Intent(getActivity(),LoginActivity.class);
                getActivity().startActivity(intent);
                Toast.makeText(getContext(), "Logged Out", Toast.LENGTH_SHORT).show();
            }
        });

        new profile().execute();
        return view;

    }

        class profile extends AsyncTask{

            @Override
            protected Object doInBackground(Object[] objects) {
                user = new User();
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
                                    user = document.toObject(User.class);
                                    Log.i("User => ", "" + user);
                                    eTxtEmail.setText(user.email);

                                    eTxtUsername.setText(user.username);
                                    eTxtMobile.setText(user.phone);

                                    String pass=user.password;
                                    String mask = pass.replaceAll("\\w(?=\\w{4})", "*");
                                    eTxtPassword.setText(mask);
                                } else {
                                    Log.i("Username", "Error getting documents.", task.getException());
                                }
                            }
                        });








                return null;

            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);

            Log.i("progess dismiss","progressbar");
                progressDialog.dismiss();

            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressDialog=new ProgressDialog(getContext());
                progressDialog.setMessage("Loading Profile");
                progressDialog.setCancelable(false);
                progressDialog.show();
                Log.i("progess show","progressbar");

            }
        }












    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }


}
