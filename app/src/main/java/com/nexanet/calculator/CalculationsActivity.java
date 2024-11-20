package com.nexanet.calculator;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class CalculationsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculations);

        TextView txtDataView = findViewById(R.id.txtDataView);

        Calculate calculate = new Calculate();
        calculate.calc(getIntent().getStringExtra("task"));
        String[] data = {""};
        calculate.resultList.forEach(it -> data[0] += it + "\n");
        txtDataView.setText(data[0]);

        loadAd();
    }

    private void loadAd() {

        AdView adView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        AdView adView1 = findViewById(R.id.adView1);
        AdRequest adRequest1 = new AdRequest.Builder().build();
        adView1.loadAd(adRequest1);

        AdView adView2 = findViewById(R.id.adView2);
        AdRequest adRequest2 = new AdRequest.Builder().build();
        adView2.loadAd(adRequest2);

        AdView adView3 = findViewById(R.id.adView3);
        AdRequest adRequest3 = new AdRequest.Builder().build();
        adView3.loadAd(adRequest3);

        AdView adView4 = findViewById(R.id.adView4);
        AdRequest adRequest4 = new AdRequest.Builder().build();
        adView4.loadAd(adRequest4);

        AdView adView5 = findViewById(R.id.adView5);
        AdRequest adRequest5 = new AdRequest.Builder().build();
        adView5.loadAd(adRequest5);

        AdView adView6 = findViewById(R.id.adView6);
        AdRequest adRequest6 = new AdRequest.Builder().build();
        adView6.loadAd(adRequest6);

        AdView adView7 = findViewById(R.id.adView7);
        AdRequest adRequest7 = new AdRequest.Builder().build();
        adView7.loadAd(adRequest7);

        AdView adView8 = findViewById(R.id.adView8);
        AdRequest adRequest8 = new AdRequest.Builder().build();
        adView8.loadAd(adRequest8);

        AdView adView9 = findViewById(R.id.adView9);
        AdRequest adRequest9 = new AdRequest.Builder().build();
        adView9.loadAd(adRequest9);

        AdView adView10 = findViewById(R.id.adView10);
        AdRequest adRequest10 = new AdRequest.Builder().build();
        adView10.loadAd(adRequest10);

        AdView adView11 = findViewById(R.id.adView11);
        AdRequest adRequest11 = new AdRequest.Builder().build();
        adView11.loadAd(adRequest11);

        AdView adView12 = findViewById(R.id.adView12);
        AdRequest adRequest12 = new AdRequest.Builder().build();
        adView12.loadAd(adRequest12);

        AdView adView13 = findViewById(R.id.adView13);
        AdRequest adRequest13 = new AdRequest.Builder().build();
        adView13.loadAd(adRequest13);

        AdView adView14 = findViewById(R.id.adView14);
        AdRequest adRequest14 = new AdRequest.Builder().build();
        adView14.loadAd(adRequest14);
    }
}