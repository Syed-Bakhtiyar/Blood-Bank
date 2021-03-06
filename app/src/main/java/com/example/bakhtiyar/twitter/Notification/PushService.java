package com.example.bakhtiyar.twitter.Notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.bakhtiyar.twitter.Home;
import com.example.bakhtiyar.twitter.R;
import com.example.bakhtiyar.twitter.UsersPost;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

/**
 * Created by Bakhtiyar on 12/21/2016.
 */
public class PushService extends Service {
    private FirebaseUser currentUser;

    private  FirebaseAuth firebaseAuth;

    UsersPost data;

    Bundle bundle;

    String uid;

    public PushService() {
    }

    @Override
    public void onCreate() {
        currentUser = FirebaseAuth.getInstance().getCurrentUser();

        firebaseAuth = FirebaseAuth.getInstance();

        currentUser = FirebaseAuth.getInstance().getCurrentUser();

//        Log.v("USER", "" + currentUser.getUid());
//
//
//
//
//
//        //get messages from notification node
//

        Query q = FirebaseDatabase.getInstance().getReference().child("Posting").limitToLast(1);


        q.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot daata : dataSnapshot.getChildren()){

                    data = daata.getValue(UsersPost.class);

                }
            if(data !=null) {
                notif(data);
            }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        super.onCreate();
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    public IBinder onBind(Intent intent) {
        return null;
    }

    public void notif(UsersPost usersPost) {


        Intent intent = new Intent(this, Home.class);



        if(FirebaseAuth.getInstance().getCurrentUser() != null) {

            //view of notification
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

            Notification.Builder notification = new Notification.Builder(this)
                    .setTicker("University")
                    .setContentTitle(usersPost.getName())
                    .setContentText(usersPost.getBlood())
                    .setTicker("Notification form my app")
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setAutoCancel(true)
                    .setVibrate(new long[]{500, 500})
                    .setSound(Settings.System.DEFAULT_NOTIFICATION_URI)
                    .setPriority(Notification.PRIORITY_HIGH)
                    .setContentIntent(pendingIntent);

            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            Random random = new Random();
            int randomNumber = random.nextInt(9999 - 1000) + 1000;
            notificationManager.notify(randomNumber, notification.build());
        }
    }
    }
