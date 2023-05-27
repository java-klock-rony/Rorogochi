package com.example.rorogochi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ShopActivity extends AppCompatActivity {

    private ListView itemListListView;
    private Button exitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        itemListListView = findViewById(R.id.itemListListView);
        exitButton = findViewById(R.id.exitButton);

        List<String> itemList = createItemList();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, itemList);
        itemListListView.setAdapter(adapter);

        itemListListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = (String) parent.getItemAtPosition(position);
                showConfirmationDialog(selectedItem);
            }
        });

        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private List<String> createItemList() {
        List<String> itemList = new ArrayList<>();

        itemList.add("Cookie (30 coins)");
        itemList.add("Sandwich (50 coins)");
        itemList.add("Pizza (70 coins)");
        itemList.add("Vegetables (10 coins)");
        itemList.add("Fruits (10 coins)");
        itemList.add("Dog biscuit (30 coins)");
        itemList.add("Muffins (40 coins)");
        itemList.add("Meat (100 coins)");
        itemList.add("Water (20 coins)");
        itemList.add("Juice (30 coins)");
        itemList.add("Soda (50 coins)");

        return itemList;
    }

    private void showConfirmationDialog(String selectedItem) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirmation")
                .setMessage("Do you want to buy " + selectedItem + "?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        showPurchaseDialog(selectedItem);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .show();
    }

    private void showPurchaseDialog(String selectedItem) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Purchase Successful")
                .setMessage("You just bought " + selectedItem + "!")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }
}