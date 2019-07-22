package com.example.secretnotifications;

import android.os.Bundle;
import android.os.Environment;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class EndOfExperiment extends AppCompatActivity {

    public String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);
        userId = getIntent().getStringExtra("USER_ID");

        //Get the text file
        String file2Name = "user" + userId + "Order2.txt";
        String file3Name = "user" + userId + "Order3.txt";
        String file4Name = "user" + userId + "Order4.txt";

        StringBuffer fileContent = new StringBuffer("");

        fileContent.append("Order 2:\n");
        fileContent = addToFinalTxt(fileContent, file2Name);
        fileContent.append("\nOrder 3:\n");
        fileContent = addToFinalTxt(fileContent, file3Name);
        fileContent.append("\nOrder 4:\n");
        fileContent = addToFinalTxt(fileContent, file4Name);


//        try {
//            fis = this.openFileInput( file2Name );
//            try {
//                while( (ch = fis.read()) != -1)
//                    fileContent.append((char)ch);
//            } catch (IOException e) {
//                e.printStackTrace();
//                Toast.makeText(this, "failed - io", Toast.LENGTH_LONG).show();
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//            Toast.makeText(this, "failed - file not found - " + file2.getAbsolutePath(), Toast.LENGTH_LONG).show();
//        }

        //Find the view by its id
        TextView tv = (TextView)findViewById(R.id.textView11);
        //Set the text
        tv.setText(fileContent.toString());
    }

    public StringBuffer addToFinalTxt(StringBuffer fileContent, String fileName) {
        int ch;
        FileInputStream fis;
        try {
            fis = this.openFileInput( fileName );
            try {
                while( (ch = fis.read()) != -1)
                    fileContent.append((char)ch);
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(this, "failed - io", Toast.LENGTH_LONG).show();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(this, "failed - file not found - ", Toast.LENGTH_LONG).show();
        }
        return fileContent;
    }

}
