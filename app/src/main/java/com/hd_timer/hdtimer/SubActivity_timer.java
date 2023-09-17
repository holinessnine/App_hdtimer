package com.hd_timer.hdtimer;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class SubActivity_timer extends AppCompatActivity {

    private Button bt_1_min;
    private TextView bt_1_text;
    private Button bt_1_plus;
    private Button bt_2_min;
    private TextView bt_2_text;
    private Button bt_2_plus;
    private Button bt_3_min;
    private TextView bt_3_text;
    private Button bt_3_plus;
    private Button bt_4_min;
    private TextView bt_4_text;
    private Button bt_4_plus;
    private Button timer_bt_start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_timer);

        // 1. 참가 인원 설정
        bt_1_min = findViewById(R.id.bt_1_min);
        bt_1_text = findViewById(R.id.bt_1_text);
        bt_1_plus = findViewById(R.id.bt_1_plus);

        //// 1-1. 감소
        bt_1_min.setOnClickListener(view -> {

            // 현재 값 가져오기
            String currentValueStr = bt_1_text.getText().toString();
            int currentValue = Integer.parseInt(currentValueStr);

            // 값 감소시키기
            if (currentValue > 1) {
                currentValue--;
            }
            else {
                showWarning("최소 1명은 필요합니다!");
            }
            // 감소된 값 업데이트
            bt_1_text.setText(String.valueOf(currentValue));
        });

        //// 1-2. 증가
        bt_1_plus.setOnClickListener(view -> {
            // 현재 값 가져오기
            String currentValueStr = bt_1_text.getText().toString();
            int currentValue = Integer.parseInt(currentValueStr);

            // 값 증가시키기: 최대 10명
            if (currentValue < 10) {
                currentValue++;
            }
            else {
                showWarning("최대 인원입니다!");
            }
            // 감소된 값 업데이트
            bt_1_text.setText(String.valueOf(currentValue));
        });

        // 2. 시작 칩 개수 설정
        final int[] chips = {100, 500, 1000, 1500, 2000, 2500, 3000, 3500, 4000, 5000};
        final int[] currentIndex_chips = {2}; // 초기 인덱스를 1로 설정

        bt_2_min = findViewById(R.id.bt_2_min);
        bt_2_text = findViewById(R.id.bt_2_text);
        bt_2_plus = findViewById(R.id.bt_2_plus);

        //// 2-1. 감소
        bt_2_min.setOnClickListener(v -> {
            if (currentIndex_chips[0] > 0) {
                currentIndex_chips[0]--;
                bt_2_text.setText(String.valueOf(chips[currentIndex_chips[0]]));
            }
            else {
                showWarning("500이 최소입니다!");
            }
        });

        //// 2-2. 증가
        bt_2_plus.setOnClickListener(v -> {
            if (currentIndex_chips[0] < chips.length - 1) {
                currentIndex_chips[0]++;
                bt_2_text.setText(String.valueOf(chips[currentIndex_chips[0]]));
            }
            else {
                showWarning("5000이 최대입니다!");
            }
        });

        // 3. 시작 블라인드 설정
        final int[] blinds = {25, 50, 100};
        final int[] currentIndex_blinds = {0}; // 초기 인덱스를 1로 설정

        bt_3_min = findViewById(R.id.bt_3_min);
        bt_3_text = findViewById(R.id.bt_3_text);
        bt_3_plus = findViewById(R.id.bt_3_plus);

        //// 3-1. 감소
        bt_3_min.setOnClickListener(v -> {
            if (currentIndex_blinds[0] > 0) {
                currentIndex_blinds[0]--;
                bt_3_text.setText(String.valueOf(blinds[currentIndex_blinds[0]]));
            }
            else {
                showWarning("25가 최소입니다!");
            }
        });

        //// 3-2. 증가
        bt_3_plus.setOnClickListener(v -> {
            if (currentIndex_blinds[0] < blinds.length - 1) {
                currentIndex_blinds[0]++;
                bt_3_text.setText(String.valueOf(blinds[currentIndex_blinds[0]]));
            }
            else {
                showWarning("100이 최대입니다!");
            }
        });

        // 4. 게임 시간 설정
        final int[] times = {30, 60, 90, 120, 150, 180, 210, 240};
        final int[] currentIndex_times = {1}; // 초기 인덱스를 1로 설정

        bt_4_min = findViewById(R.id.bt_4_min);
        bt_4_text = findViewById(R.id.bt_4_text);
        bt_4_plus = findViewById(R.id.bt_4_plus);

        //// 4-1. 감소
        bt_4_min.setOnClickListener(v -> {
            if (currentIndex_times[0] > 0) {
                currentIndex_times[0]--;
                bt_4_text.setText(String.valueOf(times[currentIndex_times[0]]));
            }
            else {
                showWarning("30분이 최소입니다!");
            }
        });

        //// 4-2. 증가
        bt_4_plus.setOnClickListener(v -> {
            if (currentIndex_times[0] < times.length - 1) {
                currentIndex_times[0]++;
                bt_4_text.setText(String.valueOf(times[currentIndex_times[0]]));
            }
            else {
                showWarning("건강을 위해 더 이상 선택할 수 없습니다!");
            }
        });

        // 게임 시작 -> 타이머 화면으로 전환
        int bt1Value = Integer.parseInt(bt_1_text.getText().toString());
        int bt2Value = Integer.parseInt(bt_2_text.getText().toString());
        int bt3Value = Integer.parseInt(bt_3_text.getText().toString());
        int bt4Value = Integer.parseInt(bt_4_text.getText().toString());
        int[] myArray = {bt1Value, bt2Value, bt3Value, bt4Value};

        timer_bt_start = findViewById(R.id.timer_bt_start);
        timer_bt_start.setOnClickListener(view -> {
            Intent intent_timer = new Intent(SubActivity_timer.this , SubActivity_timer2.class);
            intent_timer.putExtra("myArray", myArray);
            startActivity(intent_timer);
        });
    }

    // 경고 메시지
    private void showWarning(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message).setPositiveButton("확인", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        builder.create().show();
    }
}