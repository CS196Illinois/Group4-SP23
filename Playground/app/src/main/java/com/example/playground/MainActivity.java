package com.example.playground;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.media.MediaPlayer;




public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Interactive buttons
        Button button1 = findViewById(R.id.MakeString);
        Button button2 = findViewById(R.id.Print);
        Button playnote = findViewById(R.id.playnote);
        //Main buttons
        Button play = findViewById(R.id.playbutton);
        Button stop = findViewById(R.id.stopbutton);
        Button restart = findViewById(R.id.restartbutton);


        //Variables
        final String[] text = new String[1];
        final int[] mLastX = new int[1];
        final int[] mLastY = new int[1];

        //get initial position
        ViewGroup.MarginLayoutParams params1 = (ViewGroup.MarginLayoutParams) button1.getLayoutParams();
        ViewGroup.MarginLayoutParams params2 = (ViewGroup.MarginLayoutParams) button2.getLayoutParams();
        ViewGroup.MarginLayoutParams params3 = (ViewGroup.MarginLayoutParams) playnote.getLayoutParams();

        // initial states
        button1.setEnabled(false);
        button2.setEnabled(false);
        playnote.setEnabled(false);

        //Initialization for the note


        //handle case where restart is pressed and buttons comeback to their initial state and position
        play.setOnClickListener(
                new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button1.setEnabled(true);
                button2.setEnabled(true);
                playnote.setEnabled(true);
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button1.setEnabled(false);
                button2.setEnabled(false);
                playnote.setEnabled(false);
            }
        });
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button1.setEnabled(false);
                button2.setEnabled(false);
                playnote.setEnabled(false);

                button1.setLayoutParams(params1);
                button2.setLayoutParams(params2);
                playnote.setLayoutParams(params3);
            }
        });
        button1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int x = (int) motionEvent.getRawX();
                int y = (int) motionEvent.getRawY();

                switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {
                    case MotionEvent.ACTION_DOWN:
                        mLastX[0] = x;
                        mLastY[0] = y;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        int dx = x - mLastX[0];
                        int dy = y - mLastY[0];

                        int newX = view.getLeft() + dx;
                        int newY = view.getTop() + dy;

                        view.layout(newX, newY, newX + view.getWidth(), newY + view.getHeight());

                        mLastX[0] = x;
                        mLastY[0] = y;
                        break;
                    case MotionEvent.ACTION_UP:
                        // Handle button release
                        view.performClick(); // Manually trigger onClick()
                        break;
                }
                return true;
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a dialog to get text input from the user
                final EditText input = new EditText(MainActivity.this);
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Enter Text")
                        .setMessage("Please enter some text:")
                        .setView(input)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                text[0] = input.getText().toString();
                                // Do something with the text input
                                Toast.makeText(MainActivity.this, "You entered: " + text[0], Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                // Do nothing
                            }
                        })
                        .show();

            }
        });
        button2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int x = (int) motionEvent.getRawX();
                int y = (int) motionEvent.getRawY();

                switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {
                    case MotionEvent.ACTION_DOWN:
                        mLastX[0] = x;
                        mLastY[0] = y;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        int dx = x - mLastX[0];
                        int dy = y - mLastY[0];

                        int newX = view.getLeft() + dx;
                        int newY = view.getTop() + dy;

                        view.layout(newX, newY, newX + view.getWidth(), newY + view.getHeight());

                        mLastX[0] = x;
                        mLastY[0] = y;
                        break;
                    case MotionEvent.ACTION_UP:
                        // Handle button release
                        view.performClick(); // Manually trigger onClick()
                        break;
                }
                return true;
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a dialog to get text input from the user
                final TextView input = new TextView(MainActivity.this);
                new AlertDialog.Builder(MainActivity.this)
                        .setMessage(text[0])
                        .setView(input)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                // Do nothing
                            }
                        })
                        .show();
            }
        });

        //functionality for play note button
        playnote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //search through mp3 files
                String input = text[0].toLowerCase();
                int resId = getResources().getIdentifier(input, "raw", getPackageName());
                //Play note
                if (resId != 0) {
                    MediaPlayer mediaPlayer = MediaPlayer.create(MainActivity.this, resId);
                    mediaPlayer.start();
                } else {
                    Toast.makeText(MainActivity.this, "File not found", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Make button dragable
        playnote.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int x = (int) motionEvent.getRawX();
                int y = (int) motionEvent.getRawY();

                switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {
                    case MotionEvent.ACTION_DOWN:
                        mLastX[0] = x;
                        mLastY[0] = y;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        int dx = x - mLastX[0];
                        int dy = y - mLastY[0];

                        int newX = view.getLeft() + dx;
                        int newY = view.getTop() + dy;

                        view.layout(newX, newY, newX + view.getWidth(), newY + view.getHeight());

                        mLastX[0] = x;
                        mLastY[0] = y;
                        break;
                    case MotionEvent.ACTION_UP:
                        // Handle button release
                        view.performClick(); // Manually trigger onClick()
                        break;
                }
                return true;
            }
        });


    }

}