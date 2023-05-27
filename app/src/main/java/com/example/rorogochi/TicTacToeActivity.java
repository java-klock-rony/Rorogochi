package com.example.rorogochi;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class TicTacToeActivity extends AppCompatActivity {

    private final List<int[]> combinationList = new ArrayList<>();
    private int[] boxPositions = {0, 0, 0, 0, 0, 0, 0, 0, 0}; // 9 zero
    private int playerTurn = 1;
    private int totalSelectedBoxes = 1;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tictactoe);

        combinationList.add(new int[]{0, 1, 2});
        combinationList.add(new int[]{3, 4, 5});
        combinationList.add(new int[]{6, 7, 8});
        combinationList.add(new int[]{0, 3, 6});
        combinationList.add(new int[]{1, 4, 7});
        combinationList.add(new int[]{2, 5, 8});
        combinationList.add(new int[]{2, 4, 6});
        combinationList.add(new int[]{0, 4, 8});

        String getPlayerOneName = getIntent().getStringExtra("playerOne");
        String getPlayerTwoName = getIntent().getStringExtra("playerTwo");

        //To fix
        TextView playerOneName = findViewById(R.id.playerOneName);
        TextView playerTwoName = findViewById(R.id.playerTwoName);

        Toast.makeText(TicTacToeActivity.this, "Player One: " + getPlayerOneName + ", Player Two: " + getPlayerTwoName, Toast.LENGTH_SHORT).show();

        findViewById(R.id.image1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBoxSelectable(0)){
                    performAction((ImageView) view, 0);
                }
            }
        });

        findViewById(R.id.image2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBoxSelectable(1)){
                    performAction((ImageView) view, 1);
                }
            }
        });

        findViewById(R.id.image3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBoxSelectable(2)){
                    performAction((ImageView) view, 2);
                }
            }
        });

        findViewById(R.id.image4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBoxSelectable(3)){
                    performAction((ImageView) view, 3);
                }
            }
        });

        findViewById(R.id.image5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBoxSelectable(4)){
                    performAction((ImageView) view, 4);
                }
            }
        });

        findViewById(R.id.image6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBoxSelectable(5)){
                    performAction((ImageView) view, 5);
                }
            }
        });

        findViewById(R.id.image7).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBoxSelectable(6)){
                    performAction((ImageView) view, 6);
                }
            }
        });

        findViewById(R.id.image8).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBoxSelectable(7)){
                    performAction((ImageView) view, 7);
                }
            }
        });

        findViewById(R.id.image9).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBoxSelectable(8)){
                    performAction((ImageView) view, 8);
                }
            }
        });





    }

    private void performAction(ImageView imageView, int selectedBoxPosition) {
        boxPositions[selectedBoxPosition] = playerTurn;

        if (playerTurn == 1) {
            imageView.setImageResource(R.drawable.ximage);
            if (checkResults()) {
                ResultDialog resultDialog = new ResultDialog(TicTacToeActivity.this, "Player 1 win !", TicTacToeActivity.this);
                resultDialog.setCancelable(false);
                resultDialog.show();
            } else if (totalSelectedBoxes == 9) {
                ResultDialog resultDialog = new ResultDialog(TicTacToeActivity.this, "Match Draw", TicTacToeActivity.this);
                resultDialog.setCancelable(false);
                resultDialog.show();
            } else {
                changePlayerTurn(2);
                totalSelectedBoxes++;
            }
        } else {
            imageView.setImageResource(R.drawable.oimage);
            if (checkResults()) {
                ResultDialog resultDialog = new ResultDialog(TicTacToeActivity.this, "Player 2 win !", TicTacToeActivity.this);
                resultDialog.setCancelable(false);
                resultDialog.show();
            } else if (totalSelectedBoxes == 9) {
                ResultDialog resultDialog = new ResultDialog(TicTacToeActivity.this, "Match Draw", TicTacToeActivity.this);
                resultDialog.setCancelable(false);
                resultDialog.show();
            } else {
                changePlayerTurn(1);
                totalSelectedBoxes++;
            }
        }
    }

    private void changePlayerTurn(int currentPlayerTurn) {
        playerTurn = currentPlayerTurn;
        if (playerTurn == 1) {
            findViewById(R.id.playerOneLayout).setBackgroundResource(R.drawable.black_border);
            findViewById(R.id.playerTwoLayout).setBackgroundResource(R.drawable.white_box);
        } else {
            findViewById(R.id.playerTwoLayout).setBackgroundResource(R.drawable.black_border);
            findViewById(R.id.playerOneLayout).setBackgroundResource(R.drawable.white_box);
        }
    }

    private boolean checkResults() {
        boolean response = false;
        for (int i = 0; i < combinationList.size(); i++) {
            final int[] combination = combinationList.get(i);

            if (boxPositions[combination[0]] == playerTurn && boxPositions[combination[1]] == playerTurn &&
                    boxPositions[combination[2]] == playerTurn) {
                response = true;
            }
        }
        return response;
    }

    private boolean isBoxSelectable(int boxPosition) {
        boolean response = false;
        if (boxPositions[boxPosition] == 0) {
            response = true;
        }
        return response;
    }

    public void restartMatch() {
        boxPositions = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
        playerTurn = 1;
        totalSelectedBoxes = 1;


        ImageView image1 = findViewById(R.id.image1);
        ImageView image2 = findViewById(R.id.image2);
        ImageView image3 = findViewById(R.id.image3);
        ImageView image4 = findViewById(R.id.image4);
        ImageView image5 = findViewById(R.id.image5);
        ImageView image6 = findViewById(R.id.image6);
        ImageView image7 = findViewById(R.id.image7);
        ImageView image8 = findViewById(R.id.image8);
        ImageView image9 = findViewById(R.id.image9);

        image1.setImageResource(R.drawable.white_box);
        image2.setImageResource(R.drawable.white_box);
        image3.setImageResource(R.drawable.white_box);
        image4.setImageResource(R.drawable.white_box);
        image5.setImageResource(R.drawable.white_box);
        image6.setImageResource(R.drawable.white_box);
        image7.setImageResource(R.drawable.white_box);
        image8.setImageResource(R.drawable.white_box);
        image9.setImageResource(R.drawable.white_box);
    }

}
