package com.example.bookmybook;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookmybook.Adapters.HomeAdapter;
import com.example.bookmybook.Models.BookModel;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    ArrayList<BookModel> list;
    RecyclerView HomeRV;
    ImageView AddBook,UserProfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        DatabaseHandler db=new DatabaseHandler(this);
        list=db.getAllBooks();
//        list.add(new BookModel("Computer Networks","Tabish","https://firebasestorage.googleapis.com/v0/b/library-management-3c5ed.appspot.com/o/images%2Ff69a66a3-9b91-482e-8b78-431b921ec2f2?alt=media&token=ee67ae30-a20c-4d23-b36d-9cb6b3582054",11,4));
        HomeAdapter adaptor=new HomeAdapter(this,list);
        HomeRV=findViewById(R.id.HomeRV);
        AddBook=findViewById(R.id.adBookActivity);
        UserProfile=findViewById(R.id.user_profile_Activity);
        HomeRV.setAdapter(adaptor);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        HomeRV.setLayoutManager(layoutManager);

        AddBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this,addBook.class));
            }
        });
        UserProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this,UserProfileActivity.class));
            }
        });
    }

}