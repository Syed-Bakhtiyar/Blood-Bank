package com.example.bakhtiyar.twitter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {

    UserInfo userInfo;

    TextView name , email, group;

    ListView listView;

    ArrayList<String> arrayList;

    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        listView = (ListView) findViewById(R.id.list_item);

        arrayList = new ArrayList<>();

        arrayList.add("Home");
        arrayList.add("Profile");



        arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, arrayList);

        listView.setAdapter(arrayAdapter);



        name= (TextView) findViewById(R.id.name);

        email = (TextView) findViewById(R.id.email);

        group = (TextView) findViewById(R.id.group);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                switch (i){

                    case 0:

                        startActivity(new Intent(ProfileActivity.this,Home.class));
                        break;
                    case 1:

                        startActivity(new Intent(ProfileActivity.this,MyPosts.class));

                        break;

                    case 2:

                        break;
                    case 3:

                        break;

                    case 4:

                        break;


                }



            }
        });


        FirebaseDatabase.getInstance().getReference().child("Users").child(StaticVariables.uid).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {


                userInfo = dataSnapshot.getValue(UserInfo.class);

                StaticVariables.userInfo = userInfo;

                name.setText(userInfo.getName());

                email.setText(userInfo.getEmail());

                group.setText(userInfo.getBloodgroup());


            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }
}
