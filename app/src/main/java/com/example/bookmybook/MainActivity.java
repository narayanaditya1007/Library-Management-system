package com.example.bookmybook;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bookmybook.Models.BookModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    ImageButton addBook;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        DatabaseHandler db=new DatabaseHandler(MainActivity.this);
//        List<BookModel> Books=db.getAllBooks();
//        System.out.println("List here");
//        for(int i=0;i<Books.size();i++){
//            System.out.println(Books.get(i).toString());
//        }

//        Intent intent=new Intent(this,HomeActivity.class);
//        startActivity(intent);
        DatabaseHandler db=new DatabaseHandler(this);


        addBook=findViewById(R.id.imageButton_addBook);
        addBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, addBook.class);
                startActivity(intent);
            }
        });
    }

}