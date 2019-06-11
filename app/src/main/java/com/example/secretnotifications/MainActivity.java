package com.example.secretnotifications;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    static final String TAG = "myApp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpLaunch();
    }

    private void setUpLaunch() {
        Button btnStart = (Button) findViewById(R.id.btnStart);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "this is a button log message");
                Toast.makeText(getApplicationContext(), "Button clicked", Toast.LENGTH_SHORT)
                        .show();

                // Launch second activity
                //Intent intent = new Intent(MainActivity.this, Running.class);
                Intent intent = Running.makeIntent(MainActivity.this);
                startActivity(intent);
            }
        });
    }
}