package com.bfr.raffletool;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class NomineeList extends AppCompatActivity {

    private static final int REQUEST_CODE = 10;

    ArrayList<Nomination> nominations;
    RecyclerView rvNominations;
    String winner;
    ArrayList<String> winners;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nominee_list);

       // User user = (User)getIntent().getSerializableExtra("age");
       nominations = (ArrayList<Nomination>) getIntent().getSerializableExtra("nominees");

        rvNominations = (RecyclerView) findViewById(R.id.rvNominees);

        NomineeAdapter adapter = new NomineeAdapter(this, nominations);
        rvNominations.setAdapter(adapter);
        rvNominations.setLayoutManager(new LinearLayoutManager(this));

        winners = new ArrayList<>();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_nomlist, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){

            case R.id.mnuGetRandom :
                selectRandomWinner();
                break;
            case R.id.mnuDisplayWinners :
                showWinners();
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void selectRandomWinner(){

        if (winners.size()==nominations.size()) {
            Toast.makeText(NomineeList.this, "Winners list is full", Toast.LENGTH_LONG).show();
        }
        else {

            getRandom();

            while ((winners.size()>0) && (checkIfAlreadyWinner())) {
                getRandom();
            }

            winners.add(winner);
            Toast.makeText(this, winner, Toast.LENGTH_SHORT).show();
        }

    }


    public void getRandom(){
        Integer size = nominations.size();
        Random rand = new Random();
        Nomination randomNom = nominations.get(rand.nextInt(size));
        winner = randomNom.getNominee();
    }

    public boolean checkIfAlreadyWinner() {
        for (int i = 0; i < winners.size(); i++) {
            if (winners.get(i).equals(winner)) {
                return true;
            }
        }
        return false;
    }

    public void showWinners(){

        Intent i = new Intent(NomineeList.this, WinnerActivity.class);
        i.putExtra("winners", winners);
        startActivityForResult(i, REQUEST_CODE);

    }

}
