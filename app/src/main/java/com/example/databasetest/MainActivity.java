package com.example.databasetest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private  MyDatabaseHelper myDatabaseHelper;
    static final String TAG="MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button create_db=findViewById(R.id.create_database);
        Button add_data=findViewById(R.id.add_data);
        Button update_data=findViewById(R.id.update_data);
        Button delete_date=findViewById(R.id.delete_data);
        Button query_data=findViewById(R.id.query_data);
        myDatabaseHelper = new MyDatabaseHelper(this, "BookStore.db", null, 4);

        //创建数据库
        create_db.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDatabaseHelper.getWritableDatabase();

            }
        });

        //向数据库中添加数据
        add_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               SQLiteDatabase db = myDatabaseHelper.getWritableDatabase();
               ContentValues values = new ContentValues();

               //方法一
                /**
                //开始组装第一条数据
                values.put("name","Android Studio");
                values.put("author","Guo Lin");
                values.put("pages",700);
                values.put("price",79);
                db.insert("Book",null,values);//插入第一条数据
                values.clear();

                //开始组装第二条数据
                values.put("name","Big Data");
                values.put("author","Wang Sheng");
                values.put("pages",770);
                values.put("price",34);
                db.insert("Book",null,values);//插入第二条数据
                values.clear();
                Toast.makeText(MainActivity.this, "成功添加数据", Toast.LENGTH_SHORT).show();
                **/

                //方法二

                db.execSQL("insert into Book(name,author,pages,price) values(?,?,?,?)",
                        new String[]{"JavaEE","WS","309","39.0"});

                db.execSQL("insert into Book(name,author,pages,price) values(?,?,?,?)",
                        new String[]{"python","DL","389","67.0"});
            }
        });

        //修改数据
        update_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //方法一
                /**
                SQLiteDatabase db = myDatabaseHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("price",19);//更新的数据
                db.update("Book",values,"name=?", new String[]{"Big Data"});//指定给哪一列更新
                Toast.makeText(MainActivity.this, "成功更新数据", Toast.LENGTH_SHORT).show();
                 **/

                //方法二
                SQLiteDatabase db = myDatabaseHelper.getWritableDatabase();
                db.execSQL("update Book set price=? where name=?",
                        new String[]{"9.99","JavaEE"});
            }
        });

        //删除数据
        delete_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = myDatabaseHelper.getWritableDatabase();
                //方法一
//                //删除页数大于750的数据
//                db.delete("Book","pages>?",new String[]{"750"});
//                Toast.makeText(MainActivity.this, "成功删除数据", Toast.LENGTH_SHORT).show();

                //方法二
                db.execSQL("delete from Book where name=?",
                        new String[]{"python"});
            }
        });

        //查询数据库
        query_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = myDatabaseHelper.getWritableDatabase();
                //方法一
                /**
                 //查询Book表中所有的数据
                 Cursor cursor=db.query("Book",null,null,null,null,null,null);
                 if (cursor.moveToFirst()) {
                 do {//遍历cursor对象，取出数据并打印
                 @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex("name"));
                 @SuppressLint("Range") String author = cursor.getString(cursor.getColumnIndex("author"));
                 @SuppressLint("Range") int pages  = cursor.getInt(cursor.getColumnIndex("pages"));
                 @SuppressLint("Range") double price = cursor.getDouble(cursor.getColumnIndex("price"));
                 Log.d(TAG,"书名："+name);
                 Log.d(TAG,"作者："+author);
                 Log.d(TAG,"页数："+pages);
                 Log.d(TAG,"价格："+price);
                 }while (cursor.moveToNext());

                 }
                 cursor.close();
                 **/

                //方法二
                db.execSQL("select * from Book",null);
            }
        });
    }
}