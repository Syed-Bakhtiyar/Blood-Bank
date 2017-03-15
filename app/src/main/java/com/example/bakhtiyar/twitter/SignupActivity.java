package com.example.bakhtiyar.twitter;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {

    EditText fname, lname, txt_email,txt_password;

    Spinner spin;

    String group=null;

    String[] blood = {"A positive","A Negative","B positive","B Negative","O Positive","O Negative"};

    ProgressDialog dialog;

    String fn,ln;

    String email,password;

    Button imageButton;

    FirebaseAuth firebaseAuth;

    UserInfo userInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        dialog = new ProgressDialog(this);


        firebaseAuth = FirebaseAuth.getInstance();

        fname = (EditText) findViewById(R.id.fname);

        lname = (EditText) findViewById(R.id.lname);

        txt_email = (EditText) findViewById(   R.id.email);

        txt_password = (EditText) findViewById(R.id.password);

        spin = (Spinner) findViewById(R.id.spin);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, blood);
        spin.setAdapter(adapter);


        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {

                group = spin.getItemAtPosition(position).toString();
                Log.v("item", (String) parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });





        findViewById(R.id.signup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Toast.makeText(SignupActivity.this, ""+group, Toast.LENGTH_SHORT).show();



                fn = fname.getText().toString().trim();

                ln = lname.getText().toString().trim();

                email = txt_email.getText().toString().trim();

                password = txt_password.getText().toString().trim();

                if(TextUtils.isEmpty(fn)){

                    Toast.makeText(SignupActivity.this, "Please write your first name", Toast.LENGTH_SHORT).show();

                    fname.setText("");

                    return;

                }

                if(TextUtils.isEmpty(ln)){

                    Toast.makeText(SignupActivity.this, "Please write your Last name", Toast.LENGTH_SHORT).show();

                    lname.setText("");

                    return;

                }
                if(TextUtils.isEmpty(email)){

                    Toast.makeText(SignupActivity.this, "Please write your Email", Toast.LENGTH_SHORT).show();

                    txt_email.setText("");

                    return;

                }

                if(TextUtils.isEmpty(password)){

                    Toast.makeText(SignupActivity.this, "Please write your Password", Toast.LENGTH_SHORT).show();

                    txt_password.setText("");

                    return;

                }

                if(group.equals(null)){

                    Toast.makeText(SignupActivity.this, "Please Select your Blood Group", Toast.LENGTH_SHORT).show();

                    return;
                }

                dialog.setMessage("Wait...");

                dialog.show();
                firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()) {



                            userInfo = new UserInfo(fn + " " + ln, email, group, firebaseAuth.getCurrentUser().getUid());



                            FirebaseDatabase.getInstance().getReference().child("Users").child(userInfo.getUid()).child("data").setValue(userInfo);

                            Toast.makeText(SignupActivity.this, "Account created Successfuly", Toast.LENGTH_SHORT).show();

                            dialog.hide();

                            finish();
                        }
                    }
                });




            }
        });



    }



}
