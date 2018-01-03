package com.example.birju_000.xpensemanager;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class home extends AppCompatActivity
        implements TextView.OnEditorActionListener, View.OnClickListener
    {
        private Button addExpenseBtn;
        private Button myExpensesBtn;
        private Button getIconBtn;
        private TextView totalExpenses;
        private ImageView iconOnline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        addExpenseBtn = (Button) findViewById(R.id.addExpenseBtn);
        addExpenseBtn.setOnClickListener(this);

        myExpensesBtn = (Button) findViewById(R.id.myExpensesBtn);
        myExpensesBtn.setOnClickListener(this);

        getIconBtn = (Button) findViewById(R.id.getIconBtn);
        getIconBtn.setOnClickListener(this);

        iconOnline = (ImageView) findViewById(R.id.iconOnline);

        totalExpenses = (TextView) findViewById((R.id.txtTotalExpenses));
        TotalExpensesTask tet = new TotalExpensesTask(getApplicationContext(), totalExpenses);
        tet.execute();
    }

        @Override
        public void onClick(View view) {
            switch ((view.getId()))
            {
                case R.id.addExpenseBtn:
                    Intent addExpense = new Intent(this, addExpense.class);
                    startActivity(addExpense);
                    break;

                case R.id.myExpensesBtn:
                    //code for listing all existing expenses
                    Intent expenses = new Intent(this, expenses.class);
                    startActivity(expenses);
                    break;

                case R.id.getIconBtn:
                    GetIconTask git = new GetIconTask(getApplicationContext(),iconOnline);
                    git.execute();
                    break;
            }
        }

        @Override
        public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
            return false;
        }

    }
