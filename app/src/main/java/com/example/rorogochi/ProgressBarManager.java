package com.example.rorogochi;

import android.os.Handler;
import android.widget.ProgressBar;

public class ProgressBarManager {
    private Handler handler;
    private Runnable decreaseBarsRunnable;
    private static final int DECREASE_INTERVAL = 20000; // 20 secondes

    private ProgressBar hungerProgressBar;
    private ProgressBar happinessProgressBar;

    public ProgressBarManager(ProgressBar hungerProgressBar, ProgressBar happinessProgressBar) {
        this.hungerProgressBar = hungerProgressBar;
        this.happinessProgressBar = happinessProgressBar;
    }

    public void startDecreaseBars() {
        handler = new Handler();
        decreaseBarsRunnable = new Runnable() {
            @Override
            public void run() {
                decreaseHungerBar();
                decreaseHappinessBar();
                handler.postDelayed(this, DECREASE_INTERVAL);
            }
        };
        handler.postDelayed(decreaseBarsRunnable, DECREASE_INTERVAL);
    }

    private void decreaseHungerBar() {
        int currentProgress = hungerProgressBar.getProgress();
        int maxProgress = hungerProgressBar.getMax();
        int decreaseAmount = (int) (maxProgress * 0.01); // 1% de la valeur maximale

        if (currentProgress > 0) {
            currentProgress -= decreaseAmount;
            if (currentProgress < 0) {
                currentProgress = 0;
            }
            hungerProgressBar.setProgress(currentProgress);
        }
    }

    private void decreaseHappinessBar() {
        int currentProgress = happinessProgressBar.getProgress();
        int maxProgress = happinessProgressBar.getMax();
        int decreaseAmount = (int) (maxProgress * 0.01); // 1% de la valeur maximale

        if (currentProgress > 0) {
            currentProgress -= decreaseAmount;
            if (currentProgress < 0) {
                currentProgress = 0;
            }
            happinessProgressBar.setProgress(currentProgress);
        }
    }

    public ProgressBar getHungerProgressBar() {
        return hungerProgressBar;
    }

    public ProgressBar getHappinessProgressBar() {
        return happinessProgressBar;
    }
}
