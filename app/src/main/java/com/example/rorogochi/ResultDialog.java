package com.example.rorogochi;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class ResultDialog extends Dialog {

    private final String message;


    private final TicTacToeActivity ticTacToeActivity;



    public ResultDialog(@NonNull Context context, String message, TicTacToeActivity ticTacToeActivity) {
        super(context);
        this.message = message;
        this.ticTacToeActivity = ticTacToeActivity;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_dialog);

        TextView messageText = findViewById(R.id.messageText);

        Button startAgainButton = findViewById(R.id.startAgainButton);
        Button exitButton = findViewById(R.id.exitButton);

        messageText.setText(message);


        startAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ticTacToeActivity.restartMatch();
                dismiss();
            }
        });


        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ticTacToeActivity.finish(); // Quitte l'activité principale (le jeu)
                getContext().startActivity(new Intent(getContext(), MainActivity.class));
            }
        });

    }
}
