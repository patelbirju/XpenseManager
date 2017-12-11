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
import android.widget.Toast;

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
                // username is assumed as email, temporary

                userNameText = (EditText) findViewById(R.id.userNameText);
                userName = userNameText.getText().toString();
                passwordText = (EditText) findViewById(R.id.passwordText);
                password = passwordText.getText().toString();

                //System.out.println("Username: "+userName.getText().toString()+", Password: "+passWord.getText().toString());
                XpenseManagerDB db = new XpenseManagerDB(getApplicationContext());
                User user = db.getUser(userName);
                if(user == null){
                    Toast.makeText(this, "Error: user not found ", Toast.LENGTH_LONG).show();
                }
                else{
                    Intent homeIntent = new Intent(this,home.class);
                    startActivity(homeIntent);
                    Toast.makeText(this, "Welcome, "+user.getFirstName(), Toast.LENGTH_LONG).show();
                }


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
