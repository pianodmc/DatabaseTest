package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

//用列表将图书信息显示出来
public class BookInfoActivity extends AppCompatActivity {

    List<Book> bookList;
    SQLiteOpenHelper myDatabaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_info);
        //用来封装查询结果
        bookList=new ArrayList<Book>();
        myDatabaseHelper = new MyDatabaseHelper(this, "BookStore.db",
                null, 6);
        SQLiteDatabase db = myDatabaseHelper.getWritableDatabase();

        //查询Book表中所有的数据
        Cursor cursor=db.query("Book",null,null,
                null,null,null,null);
        while (cursor.moveToNext()) {
//           String name=cursor.getString(1);
//           String author=cursor.getString(2);
//           double price=cursor.getDouble(3);
//           int pages=cursor.getInt(4);

            @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex("name"));
            @SuppressLint("Range") String author = cursor.getString(cursor.getColumnIndex("author"));
            @SuppressLint("Range") int pages  = cursor.getInt(cursor.getColumnIndex("pages"));
            @SuppressLint("Range") double price = cursor.getDouble(cursor.getColumnIndex("price"));

           Book book=new Book(name,author,price,pages);
           bookList.add(book);

           //找到listview
            ListView listView=findViewById(R.id.listView);
            //设置显示内容
            listView.setAdapter(new MyAdapter());
    }
    }
    class MyAdapter extends BaseAdapter{

        @Override
        //获取有多少条数据
        public int getCount() {
            return bookList.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        //系统调用，返回的View对象就会作为ListView的一个条目显示在屏幕上
        public View getView(int i, View convertView, ViewGroup viewGroup) {
            Book book=bookList.get(i);
            View view = null;
            if(convertView == null){
                //把布局文件填充成view对象
                view = View.inflate(BookInfoActivity.this, R.layout.list_item, null);

            }
            else{
                view = convertView;
            }

            //必须使用view.findViewById
            TextView name = (TextView) view.findViewById(R.id.name);
            name.setText(book.getName());

            TextView author = (TextView) view.findViewById(R.id.author);
            author.setText(book.getAuthor());

            TextView price = (TextView) view.findViewById(R.id.price);
            price.setText(book.getPrice()+"");

            TextView pages = (TextView) view.findViewById(R.id.pages);
            pages.setText(book.getPages()+"");
            return view;
        }
    }

}