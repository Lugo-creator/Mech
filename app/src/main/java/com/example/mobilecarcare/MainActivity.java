package com.example.mobilecarcare;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    protected int SECONDS = 5;
    Handler handler = new Handler();
    @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handler.removeCallbacks(runnable);
        handler.postDelayed(runnable, 1000);
    }
    private Runnable runnable = new Runnable() {
        public void run() {
            long currentMilliseconds = System.currentTimeMillis();
            SECONDS--;
            if (SECONDS > 0) {
                handler.postAtTime(this, currentMilliseconds);
                handler.postDelayed(runnable, 1000);
            } else {
                Intent it = new Intent(getApplicationContext(), com.example.mobilecarcare.ServiceSignInActivity.class);
                startActivity(it);
                handler.removeCallbacks(runnable);
                finish();
            }
        }
    };
}