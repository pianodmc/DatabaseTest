package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

//添加图书
public class AddBookActivity extends AppCompatActivity {

    SQLiteOpenHelper myDatabaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        EditText name=findViewById(R.id.name);
        EditText author=findViewById(R.id.author);
        EditText price=findViewById(R.id.price);
        EditText pages=findViewById(R.id.pages);
        Button submit=findViewById(R.id.submit);
        myDatabaseHelper = new MyDatabaseHelper(this, "BookStore.db",
                null, 6);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //获取填写的数据
                String bookName=name.getText().toString();
                String bookAuthor=author.getText().toString();
                String bookPrice=price.getText().toString();
                String bookPages= pages.getText().toString();

                SQLiteDatabase db = myDatabaseHelper.getWritableDatabase();
                ContentValues values = new ContentValues();

                //判断填入的数据是否为空

                values.put("name",bookName);
                values.put("author",bookAuthor);
                values.put("pages",bookPages);
                values.put("price",bookPrice);
                db.insert("Book",null,values);
                values.clear();
                Toast.makeText(AddBookActivity.this, "提交成功", Toast.LENGTH_SHORT).show();

                //提交成功后跳转到查看图书页面，查看所添加的数据
                Intent intent=new Intent(AddBookActivity.this,BookInfoActivity.class);
                startActivity(intent);
            }
        });
    }
}