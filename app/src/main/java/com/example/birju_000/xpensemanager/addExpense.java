package com.example.birju_000.xpensemanager;

import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Intent;
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
import android.widget.Toast;


public class addExpense extends AppCompatActivity
        implements TextView.OnEditorActionListener, View.OnClickListener, AdapterView.OnItemSelectedListener {

    private EditText dateText;
    private Button saveExpenseBtn;
    private Button calculatorBtn;

    private static String date;
    private static String amount;
    private static String location;
    private static String description;
    private static String category;

    private static final String CALCULATOR_PACKAGE = "com.android.calculator2";
    private static final String CALCULATOR_CLASS = "com.android.calculator2.Calculator";

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
        saveExpenseBtn = (Button) findViewById(R.id.saveExpenseBtn);
        calculatorBtn = (Button) findViewById(R.id.calculatorBtn);

        saveExpenseBtn.setOnClickListener(this);
        calculatorBtn.setOnClickListener(this);
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
        if(view.getId() == R.id.saveExpenseBtn)
        {
            date = ((EditText)findViewById(R.id.dateText)).getText().toString();
            amount = ((EditText)findViewById(R.id.amountText)).getText().toString();
            location = ((EditText)findViewById(R.id.locationText)).getText().toString();
            description = ((EditText)findViewById(R.id.descriptionText)).getText().toString();

            try{
                ContentValues values = new ContentValues();
                values.put(XpenseManagerDB.DATE, date);
                values.put(XpenseManagerDB.AMOUNT, amount);
                values.put(XpenseManagerDB.LOCATION, location);
                values.put(XpenseManagerDB.DESCRIPTION, description);
                values.put(XpenseManagerDB.CATEGORY, category);

                XpenseManagerDB db = new XpenseManagerDB(getApplicationContext());

                long rowID = db.insertExpense(values);
                if(rowID > 0){
                    Toast.makeText(this, "Expense Successfully added ", Toast.LENGTH_LONG).show();
                    Intent expenses = new Intent(this, expenses.class);
                    startActivity(expenses);
                }
            }
            catch (Exception ex){
                Log.e("Exception message: ",ex.getMessage());
            }
        }
        if(view.getId() == R.id.calculatorBtn)
        {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            intent.setComponent(new ComponentName(
                    CALCULATOR_PACKAGE,
                    CALCULATOR_CLASS));
            startActivity(intent);
        }
    }

    @Override
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        return false;
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        category = (String) parent.getItemAtPosition(pos);

    }
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}


