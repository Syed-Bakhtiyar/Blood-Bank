package com.example.bakhtiyar.twitter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MyPostVolunteers extends AppCompatActivity {

    String key;

    ForVolunteersAdapter forVolunteersAdapter;


    ArrayList arrayList;

    ListView listView;

    TextView textView;


    PostVolunteer postVolunteer;

    Bundle bundle;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_post_volunteers);

        bundle = getIntent().getExtras();

        key = bundle.getString("key");

        textView = (TextView) findViewById(R.id.count);

        arrayList = new ArrayList();

        forVolunteersAdapter = new ForVolunteersAdapter(getApplicationContext(),R.layout.for_volunteer_lists,arrayList);

        listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(forVolunteersAdapter);




        FirebaseDatabase.getInstance().getReference().child("Volunteer").child(key).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                postVolunteer = dataSnapshot.getValue(PostVolunteer.class);

                arrayList.add(postVolunteer);

                forVolunteersAdapter.notifyDataSetChanged();

                textView.setText(""+arrayList.size());


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
