package com.example.secretnotifications;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toast;

public class RankPatterns extends AppCompatActivity{
    private String userId;
    private Spinner rank1, rank2, rank3, rank4, rank5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pattern_choose);

        userId = getIntent().getStringExtra("USER_ID");
        rank1 = (Spinner) findViewById(R.id.rank1);
        rank2 = (Spinner) findViewById(R.id.rank2);
        rank3 = (Spinner) findViewById(R.id.rank3);
        rank4 = (Spinner) findViewById(R.id.rank4);
        rank5 = (Spinner) findViewById(R.id.rank5);
    }

    // Read patterns from txt file
    public void patternA(View v) {

    }
    public void patternB(View v) {

    }
    public void patternC(View v) {

    }
    public void patternD(View v) {

    }
    public void patternE(View v) {

    }

    public void done(View v) {
        String rank1Value = String.valueOf(rank1.getSelectedItem());
        String rank2Value = String.valueOf(rank2.getSelectedItem());
        String rank3Value = String.valueOf(rank3.getSelectedItem());
        String rank4Value = String.valueOf(rank4.getSelectedItem());
        String rank5Value = String.valueOf(rank5.getSelectedItem());

        if (rank1Value.isEmpty() || rank2Value.isEmpty() || rank3Value.isEmpty() || rank4Value.isEmpty() || rank5Value.isEmpty()) {
            Toast.makeText(RankPatterns.this, "Please rank all the patterns", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(this, Running.class);
            intent.putExtra("USER_ID", userId);
            startActivity(intent);
        }
    }
}
