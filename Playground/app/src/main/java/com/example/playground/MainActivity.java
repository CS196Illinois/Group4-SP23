package com.example.playground;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //buttons
        Button button1 = findViewById(R.id.MakeString);
        Button button2 = findViewById(R.id.Print);
        Button button3 = findViewById(R.id.play);
        //Variables
        final String[] text = new String[1];
        final int[] mLastX = new int[1];
        final int[] mLastY = new int[1];

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
        button3.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {

                                       }
                                   }
        );
    }

}