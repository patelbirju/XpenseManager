package com.example.birju_000.xpensemanager;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

    private static String firstName;
    private static String lastName;
    private static String email;
    private static String password;

    private UserDB.UserDbHelper helper;
    private SQLiteDatabase db;

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
        firstName = ((EditText) findViewById(R.id.firstNameText)).getText().toString();
        lastName = ((EditText) findViewById(R.id.lastNameText)).getText().toString();
        email = ((EditText) findViewById(R.id.emailText)).getText().toString();
        password = ((EditText) findViewById(R.id.passwordTextSignUp)).getText().toString();


        try{
            ContentValues values = new ContentValues();
            values.put(UserDB.FIRST_NAME, firstName);
            values.put(UserDB.LAST_NAME, lastName);
            values.put(UserDB.EMAIL, email);
            values.put(UserDB.PASSWORD, password);

            UserDB user = new UserDB(getApplicationContext());

            long rowID = user.insertUser(values);
            if(rowID > 0){
                Toast.makeText(this, "Account Successfully Created. "+rowID, Toast.LENGTH_LONG).show();
            }


        }
        catch (Exception ex){
            Log.e("Exception message: ",ex.getMessage());
        }


    }

    @Override
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        return false;
    }

    public static ContentValues getUserData(){
        ContentValues values = new ContentValues();
        values.put(UserDB.FIRST_NAME, firstName);
        values.put(UserDB.LAST_NAME, lastName);
        values.put(UserDB.EMAIL, email);
        values.put(UserDB.PASSWORD, password);

        return values;
    }
}
