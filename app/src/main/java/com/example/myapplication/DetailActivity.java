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

    private TextView mCountry;
    private TextView mNewCases;
    private TextView mTotalCases;
    private TextView mNewDeaths;
    private TextView mTotalDeaths;
    private TextView mNewRecovered;
    private TextView mTotalRecovered;
    private TextView mSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        //Log.d(TAG, "onCreate: Line 18");
        //get the intent
        final Intent intent = getIntent();

        String countryCode = intent.getStringExtra(INTENT_MESSAGE);
        //find tvDetailMessage TextView
        final au.edu.unsw.infs3634.covidtracker.Country country = au.edu.unsw.infs3634.covidtracker.Country.getCountry(countryCode);

        mCountry = findViewById(R.id.tvCountry);
        mNewCases = findViewById(R.id.tvNewCases);
        mTotalCases = findViewById(R.id.tvTotalCases);
        mNewDeaths = findViewById(R.id.tvNewDeaths);
        mTotalDeaths = findViewById(R.id.tvTotalDeaths);
        mNewRecovered = findViewById(R.id.tvNewRecovered);
        mTotalRecovered = findViewById(R.id.tvTotalRecovered);
        // get the message from the intent and set the value to the TextView element
        mCountry.setText(country.getCountry());
        mNewCases.setText(String.valueOf(country.getNewConfirmed()));
        mTotalCases.setText(String.valueOf(country.getTotalConfirmed()));
        mNewDeaths.setText(String.valueOf(country.getNewDeaths()));
        mTotalDeaths.setText(String.valueOf(country.getTotalDeaths()));
        mNewRecovered.setText(String.valueOf(country.getNewRecovered()));
        mTotalRecovered.setText(String.valueOf(country.getTotalRecovered()));
        // tv.setText(country.getCountry());
        mSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent webintent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/search?q=covid " + country.getCountry()));
                startActivity(webintent);
            }
        });
    }}
        // Button button = findViewById(R.id.btnShowVideo);
       // button.setOnClickListener(new View.OnClickListener(){

