package com.kstu.perrys_project.PVRS;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Aghedo on 4/20/2017.
 */

public class AppController extends Application {

    public static final String TAG = AppController.class.getSimpleName();
    public boolean isFirstStart;
    Context mcontext;

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(getBaseContext(), "App Controller started", Toast.LENGTH_LONG).show();
        Log.d(TAG, "onCreate: App Controller Started");
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        DatabaseReference Ref = FirebaseDatabase.getInstance().getReference();
        Ref.keepSynced(true);


        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                //  Intro App Initialize SharedPreferences
                SharedPreferences getSharedPreferences = PreferenceManager
                        .getDefaultSharedPreferences(getBaseContext());

                //  Create a new boolean and preference and set it to true
                isFirstStart = getSharedPreferences.getBoolean("firstStart", true);

                //  Check either activity or app is open very first time or not and do action
                if (isFirstStart) {

                    //  Launch application introduction screen
                    Intent i = new Intent(AppController.this, MyIntro.class);
                    startActivity(i);
                    SharedPreferences.Editor e = getSharedPreferences.edit();
                    e.putBoolean("firstStart", false);
                    e.apply();
                }
            }
        });
        t.start();

    }

}
