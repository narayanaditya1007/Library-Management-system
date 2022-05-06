package com.example.bookmybook;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookmybook.Adapters.ProfileIssueAdapter;
import com.example.bookmybook.Models.IssueModel;

import java.util.ArrayList;

public class UserProfileActivity extends AppCompatActivity {
    ArrayList<IssueModel> arrayList;
    RecyclerView userRV;
    TextView ret,userEmail,userName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        ret=findViewById(R.id.return_btn);
        userName=findViewById(R.id.tv_profileUsername);
        userEmail=findViewById(R.id.tv_profileEmail);


        String newString;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                newString= null;
            } else {
                newString= extras.getString("STRING I NEED");
            }
        } else {
            newString= (String) savedInstanceState.getSerializable("STRING I NEED");
        }

        DatabaseHandler db=new DatabaseHandler(this);

        userName.setText(db.usernameFromEmail(newString));
        userEmail.setText(newString);
        arrayList=db.getAllBooksIssued(newString);
//        IssueModel i=new IssueModel("narayanaditya1007@gmail.com","10/07/2002","23/06/2003",5,3);
//        arrayList.add(i);
        System.out.println("Issues");
        for(int i=0;i<arrayList.size();i++){
            IssueModel issue = new IssueModel(arrayList.get(i).getUserEmail(),arrayList.get(i).getIssueDate(),arrayList.get(i).getReturnDate(),arrayList.get(i).getIssueID(),arrayList.get(i).getBookID());
            System.out.println("id "+ issue.getIssueID());
        }
        userRV=findViewById(R.id.UserRV);

//        arrayList.add(new BookModel("Computer Networks","Tabish",11,"https://firebasestorage.googleapis.com/v0/b/library-management-3c5ed.appspot.com/o/images%2Ff69a66a3-9b91-482e-8b78-431b921ec2f2?alt=media&token=ee67ae30-a20c-4d23-b36d-9cb6b3582054","11"));
        //arrayList.add(new BookModel("Computer Networks","Tabish",11,"https://firebasestorage.googleapis.com/v0/b/library-management-3c5ed.appspot.com/o/images%2Ff69a66a3-9b91-482e-8b78-431b921ec2f2?alt=media&token=ee67ae30-a20c-4d23-b36d-9cb6b3582054","11"));
        ProfileIssueAdapter adaptor=new ProfileIssueAdapter(arrayList,this);
        userRV=findViewById(R.id.UserRV);
        userRV.setAdapter(adaptor);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        userRV.setLayoutManager(layoutManager);

    }
}