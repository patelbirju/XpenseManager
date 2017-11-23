package com.example.birju_000.xpensemanager;

import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


public class addExpense extends AppCompatActivity
        implements TextView.OnEditorActionListener, View.OnClickListener, AdapterView.OnItemSelectedListener {

    private EditText dateText;
    private Button addExpenseBtn;

    private static String amount;
    private static String location;
    private static String description;
    private static String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);

        //Supplying the spinner with the categories
        Spinner categoriesSpinner = (Spinner) findViewById(R.id.categoriesSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.categories, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categoriesSpinner.setAdapter(adapter);

        dateText = (EditText) findViewById(R.id.dateText);
        addExpenseBtn = (Button) findViewById(R.id.addExpenseBtn);

        addExpenseBtn.setOnClickListener(this);
        dateText.setOnClickListener(this);
        categoriesSpinner.setOnItemSelectedListener(this);
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.dateText)
        {
            showDatePickerDialog(view);
        }
        if(view.getId() == R.id.addExpenseBtn)
        {
            //Create expense and add it
        }
    }

    @Override
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        return false;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        String cate = (String) parent.getItemAtPosition(pos);
        Log.i("Selected category: ", cate);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}


