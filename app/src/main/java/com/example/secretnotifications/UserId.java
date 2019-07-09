package com.example.secretnotifications;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class UserId  extends AppCompatActivity {

    private EditText mEdit;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_id_enter);

        mEdit = (EditText)findViewById(R.id.editText1);
    }

    public void startBtn(View v) {
        userId = mEdit.getText().toString();

        if (userId.isEmpty()) {
            Toast.makeText(UserId.this, "Please enter User ID", Toast.LENGTH_SHORT).show();
        } else {
            // Write to file
            Intent intent = new Intent(this, RankPatterns.class);
            intent.putExtra("USER_ID", userId);
            startActivity(intent);
        }
    }

}
