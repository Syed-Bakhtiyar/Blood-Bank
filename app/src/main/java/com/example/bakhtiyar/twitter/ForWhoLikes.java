package com.example.bakhtiyar.twitter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ForWhoLikes extends AppCompatActivity {

    ListView listView;
    private ArrayList<LikesClass> likesarraylist;
    private TextView likes;
    LikesAdapter likesAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_who_likes);

        listView = (ListView) findViewById(R.id.list);

        likes = (TextView) findViewById(R.id.count);

        likesarraylist = new ArrayList<>();

        likesAdapter= new LikesAdapter(likesarraylist,this);

        listView.setAdapter(likesAdapter);


        
        
        FirebaseDatabase.getInstance().getReference().child("Likes").child(StaticVariables.arrayList.getPush()).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                LikesClass lk = dataSnapshot.getValue(LikesClass.class);

                likesarraylist.add(lk);

                likesAdapter.notifyDataSetChanged();

                likes.setText(""+likesarraylist.size());



            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

                likes.setText(""+(likesarraylist.size()-1));

//                likesarraylist = null;

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
