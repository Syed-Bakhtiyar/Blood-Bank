package com.example.bakhtiyar.twitter;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class PostReadFragment extends Fragment {

    View v;

    PostListAdapter postListAdapter;

    UsersPost usersPost;

    ArrayList<UsersPost> arrayList;

    ListView listView;


    public PostReadFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_post_read, container, false);

        listView = (ListView) v.findViewById(R.id.list);

        arrayList = new ArrayList<>();

        postListAdapter = new PostListAdapter(arrayList,getContext());

        listView.setAdapter(postListAdapter);


        FirebaseDatabase.getInstance().getReference().child("Posting").addChildEventListener(new ChildEventListener() {
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

                ForSeeVolunteer someFragment = new ForSeeVolunteer(arrayList.get(position).getPush());
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, someFragment ); // give your fragment container id in first parameter
                transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
                transaction.commit();


                //Toast.makeText(getContext(), ""+position, Toast.LENGTH_SHORT).show();

            }
        });





        return v;
    }

}
