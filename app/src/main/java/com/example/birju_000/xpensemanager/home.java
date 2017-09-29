package com.example.birju_000.xpensemanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class home extends AppCompatActivity
    {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toast.makeText(this, "Welcome Birju...", Toast.LENGTH_LONG).show();
    }
}
