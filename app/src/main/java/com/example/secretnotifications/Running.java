package com.example.secretnotifications;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Running extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_running);

        setupEndRunningMethod();
    }

    private void setupEndRunningMethod() {
        Button btnStart = (Button) findViewById(R.id.end_running);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish(); // terminates and return back to previous activity
            }
        });
    }


    public static Intent makeIntent(Context context) {
        return new Intent(context, Running.class);
    }
}
