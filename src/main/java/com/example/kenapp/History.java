package com.example.kenapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import java.util.ArrayList;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;
import android.view.ViewGroup;

import static android.view.View.SYSTEM_UI_FLAG_FULLSCREEN;
import static android.view.View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
import static android.view.View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
import static android.view.View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
import static android.view.View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
import static android.view.View.SYSTEM_UI_FLAG_LAYOUT_STABLE;

public class History extends AppCompatActivity {
    ArrayList<String> calculationsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history);

        Button clear = findViewById(R.id.clear);
        hideNavBar();

        DisplayMetrics tmp = new DisplayMetrics();      //make this page a pop-up display for the main calculator
        getWindowManager().getDefaultDisplay().getMetrics(tmp);
        getWindow().setLayout((int)(tmp.widthPixels*.7), (int)(tmp.heightPixels*.5));
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = -20;
        getWindow().setAttributes(params);

        calculationsList = getIntent().getStringArrayListExtra("calculationsList"); //get calc history array from main
        printList();                                                                      //print the list

        //clear calculation history and
//display empty page
        clear.setOnClickListener(v -> {
            calculationsList.clear();
            printList();
            MainActivity.calculationList.clear();
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    //hides upper and lower navigation bars
    private void hideNavBar(){
        this.getWindow().getDecorView().setSystemUiVisibility(
                SYSTEM_UI_FLAG_FULLSCREEN |
                        SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                        SYSTEM_UI_FLAG_IMMERSIVE_STICKY |
                        SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                        SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                        SYSTEM_UI_FLAG_LAYOUT_STABLE);
    }

    private void printList(){
        ListView tmpList = findViewById(R.id.list);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(History.this, android.R.layout.simple_list_item_1, calculationsList){
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {  //apparently cant change the text color of list view
                View view = super.getView(position, convertView, parent);                               //in the xml file
                TextView tv = (TextView) view.findViewById(android.R.id.text1);                         //this was a work around
                tv.setTextColor(Color.WHITE);

                return view;
            }
        };
        tmpList.setAdapter(myAdapter);
    }
}