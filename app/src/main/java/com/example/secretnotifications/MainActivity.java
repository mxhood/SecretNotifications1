package com.example.secretnotifications;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Spinner spinner1, spinner2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void addListenerOnSpinnerItemSelection() {
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }


    public void setUpLaunch(View view) {
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner2 = (Spinner) findViewById(R.id.spinner2);

        Toast.makeText(MainActivity.this,
                "OnClickListener : " +
                        "\nSpinner 1 : "+ String.valueOf(spinner1.getSelectedItem()) +
                        "\nSpinner 2 : "+ String.valueOf(spinner2.getSelectedItem()),
                Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, Running.class);
        startActivity(intent);
    }
}