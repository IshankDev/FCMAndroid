package com.x.notificationtest.view;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.functions.FirebaseFunctions;
import com.google.firebase.functions.HttpsCallableResult;
import com.x.notificationtest.R;

public class MainActivity extends AppCompatActivity {

    private FirebaseFunctions mFunctions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //initializing Firebase Functions
        mFunctions = FirebaseFunctions.getInstance();

        initView(); //basic function to setup view
    }

    private void initView() {

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //calling function to send Notification
                sendToDevice();
            }
        });

    }


    //function to Send Notification on device
    private Task<String> sendToDevice() {


        return mFunctions
                .getHttpsCallable("sendToDevice")
                .call()
                .continueWith(new Continuation<HttpsCallableResult, String>() {
                    @Override
                    public String then(@NonNull Task<HttpsCallableResult> task) throws Exception {
                        // This continuation runs on either success or failure, but if the task
                        // has failed then getResult() will throw an Exception which will be
                        // propagated down.
                        return (String) task.getResult().getData();
                    }
                });
    }

}
