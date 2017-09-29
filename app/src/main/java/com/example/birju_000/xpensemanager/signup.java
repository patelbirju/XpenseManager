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

public class signup extends AppCompatActivity
        implements  OnEditorActionListener, OnClickListener{

    private Button createAccountBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        createAccountBtn = (Button) findViewById(R.id.createAccountBtn);
        createAccountBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        //Perform validations on input fields and create a new account for the user
        //
        //
        //Display message to the user
        Toast.makeText(this, "Account Successfully Created.", Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        return false;
    }
}
