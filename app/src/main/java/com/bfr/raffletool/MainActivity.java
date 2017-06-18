package com.bfr.raffletool;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static com.bfr.raffletool.R.raw.peernoms;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 20;

    Spinner spSelectWeek;
    Button btnCreateList;
    String[] row;
    ArrayList<Nomination> nominations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spSelectWeek = (Spinner) findViewById(R.id.spSelectWeek);
        btnCreateList = (Button) findViewById(R.id.btnCreateList);
        btnCreateList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createList();
            }
        });

        nominations = new ArrayList<>();

        Log.e("tag", "test");
    }

    public void createList(){

        String week = spSelectWeek.getSelectedItem().toString();

        InputStream inputStream = getResources().openRawResource(peernoms);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        try {
            String csvLine;
            while ((csvLine = reader.readLine()) != null) {
                row = csvLine.split(",");
                try {
                    if (row[1].equals("Enter Name of Student ")) {
                        continue;
                    }

                    int month =  Integer.parseInt(String.valueOf(row[0].charAt(6)));
                    int day = Integer.parseInt(String.valueOf(row[0].substring(8,10)));

                    switch (week) {
                        case "Week One" : // month = 6 and day <= 23
                            if ((month == 6) && (day <= 23)) {
                                Nomination nom = new Nomination(row[0], row[1], row[2]);
                                nominations.add(nom);
                            }
                            break;
                        case "Week Two" : // month = 6 and day >= 23 <= 30
                            if ((month == 6) && (day > 23) && (day <= 30)) {
                                Nomination nom = new Nomination(row[0], row[1], row[2]);
                                nominations.add(nom);
                            }
                            break;
                        case "Week Three" : // month = 7 and day <= 7
                            if ((month == 7) && (day <= 7)) {
                                Nomination nom = new Nomination(row[0], row[1], row[2]);
                                nominations.add(nom);
                            }
                            break;
                        default:
                            break;
                    }

                } catch (Exception e) {
                    Log.e("Unknown ", e.toString());
                }
            }

            Intent i = new Intent(MainActivity.this, NomineeList.class);
            i.putExtra("nominees", nominations);
            startActivityForResult(i, REQUEST_CODE);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
