package com.example.bakhtiyar.twitter;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {


    FirebaseAuth firebaseAuth;

    FirebaseAuth.AuthStateListener firAuthStateListener;


    String email, password;


    EditText txt_email, txt_password;

    Button login;

    ProgressDialog progressDialog;

    TextView textView;

    FirebaseDatabase firebaseDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() != null){
            Toast.makeText(MainActivity.this, "SignIn", Toast.LENGTH_SHORT).show();

            StaticVariables.uid = firebaseAuth.getCurrentUser().getUid();
            startActivity(new Intent(MainActivity.this,ProfileActivity.class));
        }




        login = (Button) findViewById(R.id.login);

        textView = (TextView) findViewById(R.id.link);

        txt_email = (EditText) findViewById(R.id.name);

        txt_password = (EditText) findViewById(R.id.password);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                login();


            }
        });

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainActivity.this, SignupActivity.class));

            }
        });


    }

    public void login() {


        email = txt_email.getText().toString().trim();

        password = txt_password.getText().toString().trim();

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {

            Toast.makeText(MainActivity.this, "Please insert email or password", Toast.LENGTH_SHORT).show();

            return;
        }
        validation();


    }

    public void validation() {

        progressDialog = new ProgressDialog(this);

        progressDialog.setMessage("Wait");

        progressDialog.show();


        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {

                    progressDialog.dismiss();

                    Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();

                    StaticVariables.uid = firebaseAuth.getCurrentUser().getUid();

                    startActivity(new Intent(MainActivity.this, ProfileActivity.class));


                } else {
                    progressDialog.dismiss();
                    Toast.makeText(MainActivity.this, "Not Success", Toast.LENGTH_SHORT).show();

                }

            }
        });




//        firAuthStateListener = new FirebaseAuth.AuthStateListener() {
//            @Override
//            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//
//                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
//
//
//
//                if(firebaseUser != null){
//                    Toast.makeText(MainActivity.this, "SignIn", Toast.LENGTH_SHORT).show();
//
//                    StaticVariables.uid = firebaseAuth.getCurrentUser().getUid();
//                    startActivity(new Intent(MainActivity.this,Home.class));
//                }
//
//            }
//        };

//        firAuthStateListener = new FirebaseAuth.AuthStateListener() {
//            @Override
//            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//
//                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
//
//                if(firebaseUser != null){
//                    Toast.makeText(MainActivity.this, "SignIn", Toast.LENGTH_SHORT).show();
//
//                    onSignedInInitialize(firebaseUser.getDisplayName());
//                }else {
//                    onSignedOutCleanUp();
//                    startActivityForResult(
//                            AuthUI.getInstance()
//                                    .createSignInIntentBuilder()
//                                    .setIsSmartLockEnabled(false)
//                                    .setProviders(
//                                            AuthUI.EMAIL_PROVIDER,
//                                            AuthUI.GOOGLE_PROVIDER,
//                                            AuthUI.FACEBOOK_PROVIDER)
//                                    .build(),
//                            RC_SIGN_IN);
//                    Toast.makeText(MainActivity.this, "Signout", Toast.LENGTH_SHORT).show();
//
//                }
//
//            }
//        };


    }



    @Override
    protected void onResume() {
        super.onResume();

      //  firebaseAuth.addAuthStateListener(firAuthStateListener);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(firebaseAuth.getCurrentUser() != null) {
            firebaseAuth.removeAuthStateListener(firAuthStateListener);
        }
        //removeListner();
//        arrayList.clear();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


}
