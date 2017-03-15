package com.example.bakhtiyar.twitter;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ForSeeVolunteer extends Fragment {

    String key;

    ForVolunteersAdapter forVolunteersAdapter;

    View v;

    ArrayList arrayList;

    ListView listView;

    TextView textView;


    PostVolunteer postVolunteer;

    public ForSeeVolunteer(String key) {
        this.key = key;
    }

    public ForSeeVolunteer() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

       v = inflater.inflate(R.layout.fragment_for_see_volunteer, container, false);

        textView = (TextView) v.findViewById(R.id.count);

        arrayList = new ArrayList();

        forVolunteersAdapter = new ForVolunteersAdapter(getContext(),R.layout.for_volunteer_lists,arrayList);

        listView = (ListView) v.findViewById(R.id.list);

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



        return v;
    }

}
