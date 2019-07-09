package com.example.secretnotifications;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Spinner spinner1, spinner2, spinner3, spinner4, spinner5;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userId = getIntent().getStringExtra("USER_ID");
    }

    public void setUpLaunch(View view) {
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        spinner3 = (Spinner) findViewById(R.id.spinner3);
        spinner4 = (Spinner) findViewById(R.id.spinner4);
        spinner5 = (Spinner) findViewById(R.id.spinner5);

        String spinner1_value = String.valueOf(spinner1.getSelectedItem());
        String spinner2_value = String.valueOf(spinner2.getSelectedItem());
        String spinner3_value = String.valueOf(spinner3.getSelectedItem());
        String spinner4_value = String.valueOf(spinner4.getSelectedItem());
        String spinner5_value = String.valueOf(spinner5.getSelectedItem());



        Toast.makeText(MainActivity.this,
                "OnClickListener : " +
                        "\nSpinner 1 : "+ String.valueOf(spinner1.getSelectedItem()) +
                        "\nSpinner 2 : "+ String.valueOf(spinner2.getSelectedItem()) +
                        "\nSpinner 3 : "+ String.valueOf(spinner3.getSelectedItem()) +
                        "\nSpinner 4 : "+ String.valueOf(spinner4.getSelectedItem()) +
                        "\nSpinner 5 : "+ String.valueOf(spinner5.getSelectedItem()),
                Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, Running.class);
        intent.putExtra("USER_ID", userId);
        intent.putExtra("SPINNER_1", spinner1_value);
        intent.putExtra("SPINNER_2", spinner2_value);
        intent.putExtra("SPINNER_3", spinner3_value);
        intent.putExtra("SPINNER_4", spinner4_value);
        intent.putExtra("SPINNER_5", spinner5_value);
        startActivity(intent);
    }
}