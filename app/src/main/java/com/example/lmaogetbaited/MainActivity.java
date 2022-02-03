package com.example.lmaogetbaited;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.LinearLayout;


import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private static int lastx, lasty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button baitButton = findViewById(R.id.bait_button);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        Log.i(TAG, "width = " + width + " height = " + height);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(width/4,(int)(height/2.5),0,0);
        params.width = 500  % (width /2);
        params.height = 300 % ((int)(height /1.25));
        baitButton.setLayoutParams(params);

        lastx=width/4;
        lasty=(int)(height/2.5);

        /*baitButton.setOnClickListener(v -> {
            moveAction(baitButton, width, height, params, random);
        });*/

        Random random = new Random();
        baitButton.setOnTouchListener((v, event) -> {
            if(event.getAction() == MotionEvent.ACTION_DOWN) {
                moveAction(baitButton, width, height, params, random);
            }
            return false;
        });
    }

    private void moveAction(Button baitButton, int width, int height, LinearLayout.LayoutParams params, Random random) {
        int randx = Math.abs(random.nextInt() % (width /2));
        int randy = Math.abs(random.nextInt() % ((int)(height /1.25)));
        while(!((Math.abs(randx - lastx) > 400) || (Math.abs(randy-lasty) > 300))) {
            randx = Math.abs(random.nextInt() % (width /2));
            randy = Math.abs(random.nextInt() % ((int)(height /1.25)));
        }
        lastx=randx;
        lasty=randy;

        params.setMargins(randx, randy, 0,0 );
        baitButton.setLayoutParams(params);
    }

}