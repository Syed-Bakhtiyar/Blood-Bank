package com.example.bakhtiyar.twitter;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
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


    String email, password;


    EditText txt_email, txt_password;

    SignInButton login;

    ProgressDialog progressDialog;

    TextView textView;

    FirebaseDatabase firebaseDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        firebaseAuth = FirebaseAuth.getInstance();


        login = (SignInButton) findViewById(R.id.login);

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


    }

}
