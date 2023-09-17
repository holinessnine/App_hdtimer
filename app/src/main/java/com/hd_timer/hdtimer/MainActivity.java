package com.hd_timer.hdtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 텍스트 -> 깜빡거림
        final TextView textView = findViewById(R.id.hm_title3);
        final AlphaAnimation blinkAnimation = new AlphaAnimation(1, 0);

        blinkAnimation.setDuration(1000);
        blinkAnimation.setRepeatCount(Animation.INFINITE);
        blinkAnimation.setRepeatMode(Animation.REVERSE);
        textView.startAnimation(blinkAnimation);

        // 터치 -> 화면 넘김
        View layout = findViewById(R.id.start_layout);
        layout.setOnTouchListener((v, event) -> {
            // 터치 event 발생하면 전환
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                Intent intent = new Intent(MainActivity.this , SubActivity_hm.class);
                startNextActivity();
                return true;
            }
            return false;
        });
    }
    private void startNextActivity() {
        Intent intent = new Intent(MainActivity.this, SubActivity_hm.class);
        startActivity(intent);
    }
}