package com.example.rorogochi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class GameActivity extends AppCompatActivity {

    private ListView gameListView;
    private Button exitButton;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        gameListView = findViewById(R.id.gameListView);
        exitButton = findViewById(R.id.exitButton);

        List<String> gameList = createGameList();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, gameList);
        gameListView.setAdapter(adapter);

        gameListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedGame = (String) parent.getItemAtPosition(position);

                if (selectedGame.equals("Tic Tac Toe")) {
                    launchAddPlayers();
                } else {
                    if (selectedGame.equals("Brick Breaker")){
                        launchGameView();
                    }
                }
            }
        });

        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private List<String> createGameList() {
        List<String> gameList = new ArrayList<>();
        gameList.add("Brick Breaker");
        gameList.add("Tic Tac Toe");
        return gameList;
    }

    private void launchAddPlayers() {
        Intent intent = new Intent(GameActivity.this, AddPlayers.class);
        startActivity(intent);
    }

    private void launchGameView(){
        Intent intent = new Intent(GameActivity.this, GameView.class);
        startActivity(intent);
    }
}
