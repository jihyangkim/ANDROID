package com.dareum.wlgid.dareum_app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.dareum.wlgid.dareum_app.Login.IntentActivity;
import com.dareum.wlgid.dareum_app.Tutorial.Config;
import com.dareum.wlgid.dareum_app.Tutorial.DefaultIntro;
import com.dareum.wlgid.dareum_app.core.AppLock;
import com.dareum.wlgid.dareum_app.core.AppLockActivity;
import com.dareum.wlgid.dareum_app.core.BaseActivity;
import com.dareum.wlgid.dareum_app.core.LockManager;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //FirebaseMessaging.getInstance().subscribeToTopic("notice");

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {

        SharedPreferences sharedPreferences = getSharedPreferences(Config.FLAG, Context.MODE_PRIVATE);

        if(sharedPreferences.getBoolean(Config.FLAG,true)){
            startActivity(new Intent(MainActivity.this, DefaultIntro.class));
            SharedPreferences.Editor e=sharedPreferences.edit();

            e.putBoolean(Config.FLAG,false);

            e.apply();
        }
/*
                else if(sharedPreferences.getBoolean(Config.SIGN,true)){
                    startActivity(new Intent(MainActivity.this, SignUp.class));

                    SharedPreferences.Editor e=sharedPreferences.edit();

                    e.putBoolean(Config.SIGN,false);

                    e.apply();
                }*/
            }
        });

            int type = LockManager.getInstance().getAppLock().isPasscodeSet() ? AppLock.DISABLE_PASSLOCK
                    : AppLock.UNLOCK_PASSWORD;//true = off , false = on
           Intent intent = new Intent(this, AppLockActivity.class);
           intent.putExtra(AppLock.TYPE, type);
           startActivityForResult(intent, type);


            t.start();
            //startActivity(new Intent(MainActivity.this,SwipeActivity.class));
            startActivity(new Intent(MainActivity.this,Decision.class));

    }

}
