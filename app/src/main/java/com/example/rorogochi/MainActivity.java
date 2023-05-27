package com.example.rorogochi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ProgressBarManager progressBarManager;
    private ImageView shopImageView;
    private TextView joyTextView;
    private TextView hungerTextView;
    private CountDownTimer countDownTimer;

    private ImageView gameImageView;

    private List<Item> itemList;

    private List<Item> createItemList() {
        List<Item> itemList = new ArrayList<>();

        itemList.add(new Item("Cookie", 30));
        itemList.add(new Item("Sandwich", 50));
        itemList.add(new Item("Pizza", 100));

        return itemList;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ProgressBar hungerProgressBar = findViewById(R.id.hungerProgressBar);
        ProgressBar happinessProgressBar = findViewById(R.id.happinessProgressBar);
        shopImageView = findViewById(R.id.imageView3);
        gameImageView = findViewById(R.id.gameImageView);
        joyTextView = findViewById(R.id.joyTextView);
        hungerTextView = findViewById(R.id.hungerTextView);

        itemList = createItemList();

        progressBarManager = new ProgressBarManager(hungerProgressBar, happinessProgressBar);
        progressBarManager.startDecreaseBars();

        countDownTimer = new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                decreaseJoyAndHunger();
            }

            @Override
            public void onFinish() {
                // La minuterie est terminée.
            }
        };

        countDownTimer.start();

        gameImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGameActivity();
            }
        });

        shopImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openShopActivity();
            }
        });
    }

    private void openShopActivity() {
        Intent intent = new Intent(MainActivity.this, ShopActivity.class);
        startActivity(intent);
    }

    public void openGameActivity() {
        Intent intent = new Intent(MainActivity.this, GameActivity.class);
        startActivity(intent);
    }

    private void decreaseJoyAndHunger() {
        int currentJoy = progressBarManager.getHappinessProgressBar().getProgress();
        int currentHunger = progressBarManager.getHungerProgressBar().getProgress();

        int newJoy = Math.max(currentJoy - 10, 0);
        int newHunger = Math.max(currentHunger - 10, 0);

        progressBarManager.getHappinessProgressBar().setProgress(newJoy);
        progressBarManager.getHungerProgressBar().setProgress(newHunger);

        joyTextView.setText("JOIE : " + newJoy + "%");
        hungerTextView.setText("FAIM : " + newHunger + "%");
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        countDownTimer.cancel();
    }
}
