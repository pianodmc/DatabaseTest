package com.example.rememberpwd;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

//登录活动
public class LoginActivity extends BaseActivity {

    private EditText accountEt;
    private EditText passwordEt;
    private Button login;
    private  CheckBox remember;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        accountEt = findViewById(R.id.account);
        passwordEt = findViewById(R.id.password);
        login = findViewById(R.id.login);
        remember = findViewById(R.id.remember);
        preferences= PreferenceManager.getDefaultSharedPreferences(this);

        //实现记住用户名和密码功能
        boolean isRemember=preferences.getBoolean("remember password",false);
        if (isRemember){
            //将用户名和密码设置到文本框中
            String account=preferences.getString("account","");
            String password=preferences.getString("password","");
            accountEt.setText(account);
            passwordEt.setText(password);
            remember.setChecked(true);
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String account=accountEt.getText().toString();
                String password=passwordEt.getText().toString();

                if (account.equals("admin")&&password.equals("123456")){
                    editor=preferences.edit();
                    if (remember.isChecked()){
                        //检查复选框是否被选中
                        editor.putBoolean("remember password",true);
                        editor.putString("account",account);
                        editor.putString("password",password);
                    }
                    else {
                        editor.clear();
                    }
                    editor.apply();

                    Intent intent=new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    Toast.makeText(LoginActivity.this, "用户名和密码不匹配", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}