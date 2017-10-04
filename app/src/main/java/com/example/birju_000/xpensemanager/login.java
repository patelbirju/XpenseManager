package com.example.birju_000.xpensemanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

public class login extends AppCompatActivity
    implements  OnEditorActionListener, OnClickListener{

    private  Button loginBtn;
    private Button signUpBtn;

    private EditText userNameText;
    private EditText passwordText;

    private String userName;
    private String password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginBtn = (Button) findViewById(R.id.loginBtn);
        signUpBtn = (Button) findViewById(R.id.createAccountBtn);



        //set listeners
        loginBtn.setOnClickListener(this);
        signUpBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.loginBtn:
                //code for Login

                userNameText = (EditText) findViewById(R.id.userNameText);
                userName = userNameText.getText().toString();
                passwordText = (EditText) findViewById(R.id.passwordText);
                password = passwordText.getText().toString();

                //System.out.println("Username: "+userName.getText().toString()+", Password: "+passWord.getText().toString());



                Intent homeIntent = new Intent(this,home.class);
                startActivity(homeIntent);
                break;
            case R.id.createAccountBtn:
                //code for signup
                Intent signUpIntent = new Intent(this, signup.class);
                startActivity(signUpIntent);
                break;
        }
    }

    @Override
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        return false;
    }
}
