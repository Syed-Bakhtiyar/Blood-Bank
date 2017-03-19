package com.example.bakhtiyar.twitter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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

    ArrayList<LikesClass> likesarraylist = null;

    ListView listView;

    MyCommints myCommints;

    private int chk = 0;

    private TextView info,name,unit,hospitalrelation,urgent,phone;

    TextView likes;

    private ImageButton imageButton;
    private LikesClass likesClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commints);

        imageButton = (ImageButton) findViewById(R.id.star);
        arrayList = new ArrayList<>();



        commintListAdapter = new CommintListAdapter(arrayList,this);

        listView = (ListView) findViewById(R.id.messageListView);

        listView.setAdapter(commintListAdapter);

     //   likesarraylist = new ArrayList<>();

        likes = (TextView) findViewById(R.id.tlike);



        func();





        name = (TextView) findViewById(R.id.name);

        unit = (TextView) findViewById(R.id.units);

        hospitalrelation = (TextView) findViewById(R.id.hospitalrelation);

        urgent = (TextView) findViewById(R.id.urgent);

        phone = (TextView) findViewById(R.id.phone);

        info = (TextView) findViewById(R.id.instruction);

        name.setText(StaticVariables.arrayList.getName());

        unit.setText(StaticVariables.arrayList.getBlood() +" is required");

        hospitalrelation.setText("At "+StaticVariables.arrayList.getHospital()+" for my "+StaticVariables.arrayList.getRelation());

        urgent.setText(StaticVariables.arrayList.getUrgency());

        phone.setText("Contact at: "+StaticVariables.arrayList.getContact());

        info.setText(StaticVariables.arrayList.getInfo());

        findViewById(R.id.cardv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Commints.this,ForWhoLikes.class));

            }
        });


        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(chk == 1){

                    FirebaseDatabase.getInstance().getReference().child("Likes").child(StaticVariables.arrayList.getPush()).child(StaticVariables.uid).removeValue();

                    imageButton.setImageResource(R.drawable.blankstar);

//                    textView.setText((likesClassArrayList.size()-1)+"");
//
//                    for (int i =0;i<likesClassArrayList.size();i++){
//
//                        if (StaticVariables.uuid.equals(likesClassArrayList.get(i).getUid())){
//
//                            arrayList.remove(i);
//
//                            break;
//
//                        }
//
//
//                    }


                    chk=0;

                }
                else {

                    likesClass = new LikesClass(StaticVariables.userInfo.getName(), StaticVariables.uid, StaticVariables.arrayList.getPush());

                    FirebaseDatabase.getInstance().getReference().child("Likes").child(likesClass.getComid()).child(likesClass.getUid()).setValue(likesClass);

                    chk=1;
                }

                Toast.makeText(Commints.this, ""+"Star", Toast.LENGTH_SHORT).show();
            }
        });




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

                editText.setText("");



            }
        });


    }


    public void func(){


        FirebaseDatabase.getInstance().getReference().child("Likes").child(StaticVariables.arrayList.getPush()).child(StaticVariables.uid).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {



                if (dataSnapshot != null){
                    imageButton.setImageResource(R.drawable.yellowstar);

                    chk = 1;


                }

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
