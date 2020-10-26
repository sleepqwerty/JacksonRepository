package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
public class DetailActivity extends AppCompatActivity {
    public static final String INTENT_MESSAGE = "au.edu.unsw.infs3634.covidtracker.intent_message";
    //private String TAG = "DETAIL_ACTIVITY";
    private TextView mCountry;
    private TextView mNewCases;
    private TextView mTotalCases;
    private TextView mNewDeaths;
    private TextView mTotalDeaths;
    private TextView mNewRecovered;
    private TextView mTotalRecovered;
    private ImageView mSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        //get the intent
        final Intent intent = getIntent();
        //Retrieve message from the intent
        String countryCode = intent.getStringExtra(INTENT_MESSAGE);
        //Find the country
        final Country country = Country.getCountry(countryCode);
        //Initialise TextView objects
        mCountry = findViewById(R.id.tvCountry);
        mNewCases = findViewById(R.id.tvNewCases);
        mTotalCases = findViewById(R.id.tvTotalCases);
        mNewDeaths = findViewById(R.id.tvNewDeaths);
        mTotalDeaths = findViewById(R.id.tvTotalDeaths);
        mNewRecovered = findViewById(R.id.tvNewRecovered);
        mTotalRecovered = findViewById(R.id.tvTotalRecovered);
        mSearch = findViewById(R.id.tvSearch);
        //Set Values
        mCountry.setText(country.getCountry());
        mNewCases.setText(String.valueOf(country.getNewConfirmed()));
        mTotalCases.setText(String.valueOf(country.getTotalConfirmed()));
        mNewDeaths.setText(String.valueOf(country.getNewDeaths()));
        mTotalDeaths.setText(String.valueOf(country.getTotalDeaths()));
        mNewRecovered.setText(String.valueOf(country.getNewRecovered()));
        mTotalRecovered.setText(String.valueOf(country.getTotalRecovered()));
        //Implement setOnClickListener for search image view
        //Open google and search for covid+ country name
        mSearch.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent webintent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/search?q=covid " + country.getCountry()));
                startActivity(webintent);
            }
        });



//       //find text view object
//        TextView tv = findViewById(R.id.tvDetailMessage);
        //      // Add text to text view objct
        //    tv.setText(country.getCountry());
//
        //      Button button = findViewById(R.id.btnShowVideo);
        //    button.setOnClickListener(new View.OnClickListener(){
        //      @Override
        //    public void onClick(View v) {
        //      openYoutube();
        //}
        //});
        //}
        //public void openYoutube() {
        //  Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=Tlwda9S58Lg&ab_channel=Fireballfury"));
        //startActivity(intent);
    }
}