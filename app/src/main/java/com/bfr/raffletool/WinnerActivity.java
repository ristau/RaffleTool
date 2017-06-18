package com.bfr.raffletool;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.TextView;

import java.util.ArrayList;

public class WinnerActivity extends AppCompatActivity {

    ArrayList<String> winners;
    TextView tvResults;
    String results;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner);

        tvResults = (TextView) findViewById(R.id.tvResults);

        winners = (ArrayList<String>) getIntent().getSerializableExtra("winners");
        results = TextUtils.join(", ", winners);

        tvResults.setText(results);


    }
}
