package com.example.bakhtiyar.twitter;

import android.app.FragmentManager;
import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MyPosts extends AppCompatActivity {

    PostListAdapter postListAdapter;

    UsersPost usersPost;

    ArrayList<UsersPost> arrayList;

    ListView listView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_posts);

        listView = (ListView) findViewById(R.id.list);

        arrayList = new ArrayList<>();

        postListAdapter = new PostListAdapter(arrayList,this);

        listView.setAdapter(postListAdapter);



        FirebaseDatabase.getInstance().getReference().child("MyPosting").child(StaticVariables.uid).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                usersPost = dataSnapshot.getValue(UsersPost.class);

                arrayList.add(usersPost);

                postListAdapter.notifyDataSetChanged();



            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                postListAdapter.notifyDataSetChanged();

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



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(MyPosts.this,MyPostVolunteers.class);

                intent.putExtra("key",arrayList.get(position).getPush());

                startActivity(intent);


            }
        });


    }
}
