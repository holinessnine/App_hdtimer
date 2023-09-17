package com.hd_timer.hdtimer;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

public class SubActivity_hm extends AppCompatActivity {

    private Button bt_timer;
    private Button bt_rules;
    private Button bt_cards;
    private Button bt_terms;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_hm);

        // 1. 타이머 버튼(타이머 화면으로 전환)
        bt_timer = findViewById(R.id.bt_timer);
        bt_timer.setOnClickListener(view -> {
            Intent intent_timer = new Intent(SubActivity_hm.this , SubActivity_timer.class);
            startActivity(intent_timer);
        });

        // 2. 규칙 버튼(유튜브 링크)
        bt_rules = findViewById(R.id.bt_rules);
        bt_rules.setOnClickListener(view -> {
            String url_rules = "https://www.youtube.com/watch?v=1pOfm62mmPw";
            Intent intent_rules = new Intent(Intent.ACTION_VIEW, Uri.parse(url_rules));
            intent_rules.putExtra("android-app:auto", true);
            startActivity(intent_rules);
        });

        // 3. 족보 버튼(빈 화면 + 족보 이미지 표시)
        bt_cards = findViewById(R.id.bt_cards);
        bt_cards.setOnClickListener(view -> {
            Intent intent_cards = new Intent(SubActivity_hm.this , SubActivity_cards.class);
            startActivity(intent_cards);
        });

        // 4. 용어 버튼(나무위키 링크)
        bt_terms = findViewById(R.id.bt_terms);
        bt_terms.setOnClickListener(view -> {
            String url_terms = "https://namu.wiki/w/%ED%85%8D%EC%82%AC%EC%8A%A4%20%ED%99%80%EB%8D%A4#s-6";
            Intent intent_terms = new Intent(Intent.ACTION_VIEW, Uri.parse(url_terms));
            startActivity(intent_terms);
        });
    }
}