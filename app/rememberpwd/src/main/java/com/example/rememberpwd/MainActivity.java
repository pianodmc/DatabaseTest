package com.example.rememberpwd;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.rememberpwd.BaseActivity;

public class MainActivity  extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button=findViewById(R.id.force_offline);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new
                        Intent("com.example.rememberpwd.FORCE_OFFLINE");//强制下线
                sendBroadcast(intent);
            }
        });
    }
}