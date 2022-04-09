package com.example.litepaltest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        LitePal.initialize(this);
        Button create_database=findViewById(R.id.create_database);
        create_database.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                LitePal.getDatabase();
            }
        });
    }
}