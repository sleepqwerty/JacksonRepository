package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
    public static final String INTENT_MESSAGE = "au.edu.unsw.infs3634.covidtracker.intent_message";
    private String TAG = "DETAIL_ACTIVITY";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Log.d(TAG, "onCreate: Line 18");
        //get the intent
        Intent intent = getIntent();
        //find tvDetailMessage TextView
        TextView detailMessage = findViewById(R.id.tvDetail);
        // get the message from the intent and set the value to the TextView element
        detailMessage.setText(intent.getStringExtra(INTENT_MESSAGE));
        Button button = findViewById(R.id.btnShowVideo);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openYoutube();
            }
        });
    }
    public void openYoutube() {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=Tlwda9S58Lg&ab_channel=Fireballfury"));
        startActivity(intent);
    }
}