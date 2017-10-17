package com.example.birju_000.xpensemanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class home extends AppCompatActivity
        implements TextView.OnEditorActionListener, View.OnClickListener
    {

        private Button addExpenseBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        addExpenseBtn = (Button) findViewById(R.id.addExpenseBtn);
        addExpenseBtn.setOnClickListener(this);

        Toast.makeText(this, "Welcome Birju...", Toast.LENGTH_LONG).show();
    }

        @Override
        public void onClick(View view) {
            switch ((view.getId()))
            {
                case R.id.addExpenseBtn:
                    Intent addExpense = new Intent(this, addExpense.class);
                    startActivity(addExpense);
                    break;
            }
        }

        @Override
        public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
            return false;
        }
    }
