package com.example.bookmybook;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookmybook.Adapters.HomeAdapter;
import com.example.bookmybook.Models.BookModel;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    ArrayList<BookModel> list;
    RecyclerView HomeRV;
    HomeAdapter adaptor;
    ImageView AddBook,UserProfile,Search;
    EditText ser;
    private String newString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Search = findViewById(R.id.btn_homeSearch);
        ser=findViewById(R.id.et_homeSearch);

        Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHandler db=new DatabaseHandler(HomeActivity.this);
                String searchKey=ser.getText().toString();
                list=db.getAllBooksSearch(searchKey);
                adaptor=new HomeAdapter(HomeActivity.this,list,newString);
                HomeRV=findViewById(R.id.HomeRV);
                HomeRV.setAdapter(adaptor);
            }
        });



        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                newString= null;
            } else {
                newString= extras.getString("UserEmail");
            }
        } else {
            newString= (String) savedInstanceState.getSerializable("UserEmail");
        }
        DatabaseHandler db=new DatabaseHandler(this);
        list=db.getAllBooks();
        System.out.println("chutiya");
        for(int i=0;i<list.size();i++){
            System.out.println(list.get(i).toString());
        }
//        list.add(new BookModel("Computer Networks","Tabish","https://firebasestorage.googleapis.com/v0/b/library-management-3c5ed.appspot.com/o/images%2Ff69a66a3-9b91-482e-8b78-431b921ec2f2?alt=media&token=ee67ae30-a20c-4d23-b36d-9cb6b3582054",11,4));
        adaptor=new HomeAdapter(this,list,newString);
        HomeRV=findViewById(R.id.HomeRV);
        AddBook=findViewById(R.id.adBookActivity);
        UserProfile=findViewById(R.id.user_profile_Activity);
        HomeRV.setAdapter(adaptor);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        HomeRV.setLayoutManager(layoutManager);

        Toast.makeText(this, ""+newString, Toast.LENGTH_SHORT).show();
        adaptor.notifyDataSetChanged();

        AddBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this,addBook.class));
            }
        });
        UserProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomeActivity.this,UserProfileActivity.class);
                intent.putExtra("STRING I NEED",newString);
                startActivity(intent);
            }
        });
    }
    public void refresh(){
        adaptor.notifyDataSetChanged();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        adaptor.notifyDataSetChanged();
    }
    @Override
    protected void onResume() {
        super.onResume();
        //list.clear();
        DatabaseHandler db=new DatabaseHandler(this);
        list=db.getAllBooks();
        adaptor=new HomeAdapter(this,list,newString);
        HomeRV.setAdapter(adaptor);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        HomeRV.setLayoutManager(layoutManager);
        adaptor.notifyDataSetChanged();
    }
}