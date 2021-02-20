package com.mishsapp.chronometer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button buttonStart, buttonStop, buttonReset;
    private TextView textViewTime;
    int x;
    Runnable runnable;
    Handler handler = new Handler();

    public void init(){
        textViewTime =  findViewById(R.id.textViewTime);
        buttonStart = findViewById(R.id.buttonStart);
        buttonStop = findViewById(R.id.buttonStop);
        buttonReset = findViewById(R.id.buttonReset);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

         Start();

         Stop();

         Reset();
    }

    private void Start(){

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonStart.setEnabled(false);
                buttonStop.setEnabled(true);
                buttonStart.setText("BAŞLA");

                runnable = new Runnable() {
                    @Override
                    public void run() {
                        x++;
                        textViewTime.setText(Integer.toString(x));
                        handler.postDelayed(runnable, 1000);//her 1 saniye
                    }
                };
                handler.post(runnable);
            }
        });
    }
    private void Stop(){

        buttonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonStart.setText("DEVAM");
                buttonStart.setEnabled(true);
                buttonStop.setEnabled(false);

                handler.removeCallbacks(runnable);


            }
        });
    }
    private void Reset(){

        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonStart.setText("BAŞLA");
                buttonStop.setEnabled(true);
                buttonStart.setEnabled(true);

                handler.removeCallbacks(runnable);
                x=0;
                textViewTime.setText(Integer.toString(x));
            }
        });
    }
}
