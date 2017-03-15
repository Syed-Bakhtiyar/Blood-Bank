package com.example.bakhtiyar.twitter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Commints extends AppCompatActivity {

    EditText editText;

    Button button;

    String commint;

    CommintListAdapter commintListAdapter;

    ArrayList<MyCommints> arrayList;

    ListView listView;

    MyCommints myCommints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commints);

        arrayList = new ArrayList<>();

        commintListAdapter = new CommintListAdapter(arrayList,this);

        listView = (ListView) findViewById(R.id.messageListView);

        listView.setAdapter(commintListAdapter);

        FirebaseDatabase.getInstance().getReference().child("Commints").child(StaticVariables.push).child("UserCommints").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                myCommints = dataSnapshot.getValue(MyCommints.class);

                arrayList.add(myCommints);

                commintListAdapter.notifyDataSetChanged();
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

        editText = (EditText) findViewById(R.id.messageEditText);

        button = (Button) findViewById(R.id.sendButton);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if(charSequence.toString().trim().length()>0){
                    button.setEnabled(true);
                }else {
                    button.setEnabled(false);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                commint = editText.getText().toString().trim();

                MyCommints com = new MyCommints(commint,StaticVariables.push,StaticVariables.userInfo.getName());


                FirebaseDatabase.getInstance().getReference().child("Commints").child(StaticVariables.push).child("UserCommints").push().setValue(com);




            }
        });


    }
}
