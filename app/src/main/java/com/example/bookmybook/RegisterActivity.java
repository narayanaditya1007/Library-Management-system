package com.example.bookmybook;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bookmybook.Models.UserModel;

public class RegisterActivity extends AppCompatActivity {

    EditText email,username,password,repassword;
    TextView signinBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        DatabaseHandler db=new DatabaseHandler(this);
        email=findViewById(R.id.et_signinemail);
        username=findViewById(R.id.et_signinUsername);
        password=findViewById(R.id.et_signinPass);
        repassword=findViewById(R.id.et_signinrePass);
        signinBtn=findViewById(R.id.btn_signup);
        signinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(RegisterActivity.this, "sing up clicked", Toast.LENGTH_SHORT).show();
                String Email,Username,Password,rePassword;
                Email=email.getText().toString();
                Username=username.getText().toString();
                Password=password.getText().toString();
                rePassword=repassword.getText().toString();
//                Toast.makeText(RegisterActivity.this, ""+Email+Username+Password+rePassword, Toast.LENGTH_SHORT).show();
                if(Username.equals("") || Email.equals("")|| Password.equals("") || rePassword.equals("")){
                    Toast.makeText(RegisterActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(db.checkUsername(Email)){
                        Toast.makeText(RegisterActivity.this, "User Already Exists, Try loggin in", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        if(Password.equals(rePassword)){

                            boolean res=db.addUser(new UserModel(Username,Email,Password));
                            if(res){
                                Toast.makeText(RegisterActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                            else{
                                Toast.makeText(RegisterActivity.this, "Unsuccessful Registration! Please try again", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(RegisterActivity.this, "Both Passwords not matching", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
    }
}