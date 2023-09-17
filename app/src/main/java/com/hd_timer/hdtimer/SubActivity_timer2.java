package com.hd_timer.hdtimer;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SubActivity_timer2 extends AppCompatActivity {
    private ProgressBar progressBar;
    private Button playPauseButton;
    private CountDownTimer countDownTimer;
    private TextView textViewTime;

    private boolean isTimerRunning = false;
    private long timeRemaining = 60000; // 60 seconds (1 minute)

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_timer2);

        progressBar = findViewById(R.id.progressBar);
        playPauseButton = findViewById(R.id.playPauseButton);
        textViewTime = findViewById(R.id.textViewTime);

        progressBar.setVisibility(View.VISIBLE);

        playPauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleTimer();
            }
        });
    }

    public void toggleTimer() {
        if (isTimerRunning) {
            // 타이머 일시정지
            countDownTimer.cancel();
            isTimerRunning = false;
            playPauseButton.setText("계속");
        } else {
            // 타이머 시작
            startTimer();
            isTimerRunning = true;
            playPauseButton.setText("일시정지");
        }
    }

    public void startTimer() {
        countDownTimer = new CountDownTimer(timeRemaining, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeRemaining = millisUntilFinished;
                updateProgressBarAndTime();
            }

            @Override
            public void onFinish() {
                // 타이머 종료
                timeRemaining = 0;
                updateProgressBarAndTime();
                showToast("라운드가 종료되었습니다!");
                // 여기에서 원하는 작업을 수행할 수 있습니다.
            }
        };
        countDownTimer.start();
    }

    public void updateProgressBarAndTime() {
        int progress = (int) (100 * (1.0 - (double) timeRemaining / 60000.0));
        progressBar.setProgress(progress);
        textViewTime.setText(formatTime(timeRemaining));
    }

    public String formatTime(long millis) {
        int seconds = (int) (millis / 1000);
        int minutes = seconds / 60;
        seconds = seconds % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }

    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
