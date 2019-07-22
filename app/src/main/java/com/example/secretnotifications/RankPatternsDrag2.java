package com.example.secretnotifications;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class RankPatternsDrag2 extends AppCompatActivity implements View.OnDragListener, View.OnLongClickListener {

    private String userId;
    private Vibrator vibration;
    private Button btn1, btn2, btn3, btn4, btn5, btn6;
    public String layerA, layerB;
    public Intent intent;
    public ArrayList<String> userPatterns;
    public String currentOrder;
    public VibrationPatterns vibrationPatterns;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decide_priority_2);

        userId = getIntent().getStringExtra("USER_ID");
        vibration = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        userPatterns = getIntent().getStringArrayListExtra("ORDER");
        currentOrder = getIntent().getStringExtra("CURRENT_ORDER");
        vibrationPatterns = new VibrationPatterns();

        //Find all views and set Tag to all draggable views
        btn1 = (Button) findViewById(R.id.btnA);
        btn1.setTag("PATTERN_A");
        btn1.setOnLongClickListener(this);
        btn2 = (Button) findViewById(R.id.btnB);
        btn2.setTag("PATTERN_B");
        btn2.setOnLongClickListener(this);
        btn3 = (Button) findViewById(R.id.btnC);
        btn3.setTag("PATTERN_C");
        btn3.setOnLongClickListener(this);
        btn4 = (Button) findViewById(R.id.btnD);
        btn4.setTag("PATTERN_D");
        btn4.setOnLongClickListener(this);
        btn5 = (Button) findViewById(R.id.btnE);
        btn5.setTag("PATTERN_E");
        btn5.setOnLongClickListener(this);
        btn6 = (Button) findViewById(R.id.btnF);
        btn6.setTag("PATTERN_F");
        btn6.setOnLongClickListener(this);

        //Set Drag Event Listeners for defined layouts
        findViewById(R.id.layoutA).setOnDragListener(this);
        findViewById(R.id.layoutB).setOnDragListener(this);
        findViewById(R.id.layoutStart).setOnDragListener(this);
    }

    public void onBtn1VibrationClick(View view) {
        long[] mVibratePattern = vibrationPatterns.vib1().first;
        int[] amplitude = vibrationPatterns.vib1().second;
        if (Build.VERSION.SDK_INT >= 26) {
            vibration.vibrate(VibrationEffect.createWaveform(mVibratePattern, amplitude, -1));
        } else {
            vibration.vibrate(mVibratePattern, -1);
        }
    }
    public void onBtn2VibrationClick(View view) {
        long[] mVibratePattern = vibrationPatterns.vib2().first;
        int[] amplitude = vibrationPatterns.vib2().second;
        if (Build.VERSION.SDK_INT >= 26) {
            vibration.vibrate(VibrationEffect.createWaveform(mVibratePattern, amplitude, -1));
        } else {
            vibration.vibrate(mVibratePattern, -1);
        }
    }
    public void onBtn3VibrationClick(View view) {
        long[] mVibratePattern = vibrationPatterns.vib3().first;
        int[] amplitude = vibrationPatterns.vib3().second;
        if (Build.VERSION.SDK_INT >= 26) {
            vibration.vibrate(VibrationEffect.createWaveform(mVibratePattern, amplitude, -1));
        } else {
            vibration.vibrate(mVibratePattern, -1);
        }
    }
    public void onBtn4VibrationClick(View view) {
        long[] mVibratePattern = vibrationPatterns.vib4().first;
        int[] amplitude = vibrationPatterns.vib4().second;
        if (Build.VERSION.SDK_INT >= 26) {
            vibration.vibrate(VibrationEffect.createWaveform(mVibratePattern, amplitude, -1));
        } else {
            vibration.vibrate(mVibratePattern, -1);
        }
    }
    public void onBtn5VibrationClick(View view) {
        long[] mVibratePattern = vibrationPatterns.vib5().first;
        int[] amplitude = vibrationPatterns.vib5().second;
        if (Build.VERSION.SDK_INT >= 26) {
            vibration.vibrate(VibrationEffect.createWaveform(mVibratePattern, amplitude, -1));
        } else {
            vibration.vibrate(mVibratePattern, -1);
        }
    }

    public void onBtn6VibrationClick(View view) {
        long[] mVibratePattern = vibrationPatterns.vib6().first;
        int[] amplitude = vibrationPatterns.vib6().second;
        if (Build.VERSION.SDK_INT >= 26) {
            vibration.vibrate(VibrationEffect.createWaveform(mVibratePattern, amplitude, -1));
        } else {
            vibration.vibrate(mVibratePattern, -1);
        }
    }

    public void onBtnDoneClick(View view) {
        // check that all layouts have button and only one has one.
        if (layerA == null || layerB == null) {
            Toast.makeText(this, "Please assign a pattern to each layer", Toast.LENGTH_SHORT).show();
        } else {
            String sFileName = "user" + userId + "Order2.txt";
            String sBody = "A pattern: " + layerA + "\nB pattern: " + layerB;
            generateNoteOnSD(sFileName, sBody);
            intent = new Intent(this, Running.class);
            intent.putExtra("USER_ID", userId);
            intent.putExtra("CURRENT_ORDER", currentOrder);
            intent.putExtra("ORDER", userPatterns);
            intent.putExtra("LAYER_A_PATTERN", layerA);
            intent.putExtra("LAYER_B_PATTERN", layerB);
            startActivity(intent);
        }
    }

    public void generateNoteOnSD(String sFileName, String sBody) {
        try {
            File gpxfile = new File(this.getFilesDir(), sFileName);
            FileWriter writer = new FileWriter(gpxfile);
            writer.append(sBody);
            writer.flush();
            writer.close();
//            Toast.makeText(this, "Saved", Toast.LENGTH_LONG).show();
//            Toast.makeText(this, gpxfile.getAbsolutePath(), Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "failed", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onLongClick(View v) {
        // Create a new ClipData.Item from the ImageView object's tag
        ClipData.Item item = new ClipData.Item((CharSequence) v.getTag());
        // Create a new ClipData using the tag as a label, the plain text MIME type, and
        // the already-created item. This will create a new ClipDescription object within the
        // ClipData, and set its MIME type entry to "text/plain"
        String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};
        ClipData data = new ClipData(v.getTag().toString(), mimeTypes, item);
        // Instantiates the drag shadow builder.
        View.DragShadowBuilder dragshadow = new View.DragShadowBuilder(v);
        // Starts the drag
        v.startDrag(data        // data to be dragged
                , dragshadow   // drag shadow builder
                , v           // local data about the drag and drop operation
                , 0          // flags (not currently used, set to 0)
        );
        return true;
    }

    // This is the method that the system calls when it dispatches a drag event to the listener.
    @Override
    public boolean onDrag(View v, DragEvent event) {
        // Defines a variable to store the action type for the incoming event
        int action = event.getAction();
        final View dragView = (View) event.getLocalState();
        // Handles each of the expected events
        switch (action) {

            case DragEvent.ACTION_DRAG_STARTED:
                // Determines if this View can accept the dragged data
                if (event.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
                    // if you want to apply color when drag started to your view you can uncomment below lines
                    // to give any color tint to the View to indicate that it can accept data.
                    // v.getBackground().setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_IN);
                    // Invalidate the view to force a redraw in the new tint
                    //  v.invalidate();
                    // returns true to indicate that the View can accept the dragged data.
                    return true;
                }
                // Returns false. During the current drag and drop operation, this View will
                // not receive events again until ACTION_DRAG_ENDED is sent.
                return false;

            case DragEvent.ACTION_DRAG_ENTERED:
                // Applies a GRAY or any color tint to the View. Return true; the return value is ignored.
                v.getBackground().setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_IN);
                // Invalidate the view to force a redraw in the new tint
                v.invalidate();
                return true;

            case DragEvent.ACTION_DRAG_LOCATION:
                // Ignore the event
                return true;

            case DragEvent.ACTION_DRAG_EXITED:
                // Re-sets the color tint to blue. Returns true; the return value is ignored.
                // view.getBackground().setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_IN);
                //It will clear a color filter .
                v.getBackground().clearColorFilter();
                // Invalidate the view to force a redraw in the new tint
                v.invalidate();
                return true;

            case DragEvent.ACTION_DROP:
                // Gets the item containing the dragged data
                ClipData.Item item = event.getClipData().getItemAt(0);
                // Gets the text data from the item.
                // THIS IS THE BUTTON
                String dragData = item.getText().toString();
                // Displays a message containing the dragged data.
//                Toast.makeText(this, "Dragged data is " + dragData, Toast.LENGTH_SHORT).show();
                // Turns off any color tints
                v.getBackground().clearColorFilter();
                // Invalidates the view to force a redraw
                v.invalidate();

                View vw = (View) event.getLocalState();
                ViewGroup owner = (ViewGroup) vw.getParent();
                owner.removeView(vw); //remove the dragged view
                //caste the view into ConstraintLayout as our drag acceptable layout is ConstraintLayout
                ConstraintLayout container = (ConstraintLayout) v;
                String containerString = v.toString();
                containerString = containerString.split("app:id/")[1];
                containerString = containerString.substring(0, containerString.length() - 1);
//                Toast.makeText(this, "button is ." + dragData + ". container is ." + containerString + ".", Toast.LENGTH_SHORT).show();


                switch(containerString) {
                    case "layoutA":
                        layerA = dragData;
                        break;
                    case "layoutB":
                        layerB = dragData;
                        break;
                }

                container.addView(vw);//Add the dragged view
                float X = event.getX();
                float Y = event.getY();
                vw.setX(X-(vw.getWidth()/2));
                vw.setY(Y-(vw.getHeight()/2));
                vw.setVisibility(View.VISIBLE);//finally set Visibility to VISIBLE
                // Returns true. DragEvent.getResult() will return true.
                return true;

            case DragEvent.ACTION_DRAG_ENDED:
                // Turns off any color tinting
                v.getBackground().clearColorFilter();
                // Invalidates the view to force a redraw
                v.invalidate();

                // Does a getResult(), and displays what happened.
//                if (event.getResult())
//                    Toast.makeText(this, "The drop was handled.", Toast.LENGTH_SHORT).show();
//                else
//                    Toast.makeText(this, "The drop didn't work.", Toast.LENGTH_SHORT).show();
                // returns true; the value is ignored.
                return true;
            // An unknown action type was received.
            default:
                Log.e("DragDrop Example", "Unknown action type received by OnDragListener.");
                break;
        }
        return false;
    }
}