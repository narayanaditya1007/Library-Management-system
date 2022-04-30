package com.example.bookmybook;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class logIn extends AppCompatActivity {


    EditText username,password;
    TextView loginBtn,newUserSignIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        username=findViewById(R.id.et_loginUsername);
        password=findViewById(R.id.et_loginPassword);
        loginBtn=findViewById(R.id.btn_login);
        newUserSignIn=findViewById(R.id.tv_newUserSignin);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Username=username.getText().toString().trim();
                String Password=password.getText().toString().trim();
                DatabaseHandler db=new DatabaseHandler(logIn.this);
                if(db.checkUsername(Username)){
                    if(db.checkUsernamePassword(Username,Password)){
                        Intent intent=new Intent(getApplicationContext(),HomeActivity.class);
                        intent.putExtra("UserEmail",Username);
                        startActivity(intent);
                        finish();
                    }
                    else{
                        Toast.makeText(logIn.this, "Incorrect Password", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(logIn.this, "No such user found! Try signing in", Toast.LENGTH_SHORT).show();
                }
            }
        });

        newUserSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),RegisterActivity.class));
            }
        });
    }
}