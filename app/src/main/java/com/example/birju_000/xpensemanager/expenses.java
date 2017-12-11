package com.example.birju_000.xpensemanager;

import android.app.ActionBar;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.app.Activity;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;
import java.util.List;

public class expenses extends Activity{


    SimpleCursorAdapter mAdapter;

    private ListView lstExpenses;
    private List<Expense> expenses = new ArrayList<>();


    static final String[] PROJECTION = new String[] {XpenseManagerDB.DATE, XpenseManagerDB.LOCATION, XpenseManagerDB.AMOUNT};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses);

       lstExpenses = (ListView) findViewById(R.id.lstExpenses);

        XpenseManagerDB db = new XpenseManagerDB(getApplicationContext());
        expenses = db.getExpenses();

        /*ProgressBar progressBar = new ProgressBar(this);
        progressBar.setLayoutParams(new ActionBar.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER));
        progressBar.setIndeterminate(true);
        getListView.setEmptyView(progressBar);
        */

        populateListView();
    }

    public  void populateListView(){
        XpenseManagerDB db = new XpenseManagerDB(getApplicationContext());
        Cursor cursor = db.getAllExpenses();

        //setup mapping from cursor to view fields
        String[] fromColumns = {XpenseManagerDB.DATE, XpenseManagerDB.LOCATION, XpenseManagerDB.AMOUNT};
        int[] toViews = {R.id.expense_date_text, R.id.expense_location_text, R.id.expense_amount_text};

        SimpleCursorAdapter myAdapter;
        myAdapter = new SimpleCursorAdapter(getBaseContext(),R.layout.lst_expenses_layout,cursor,fromColumns,toViews,0);
        lstExpenses.setAdapter(myAdapter);

    }
}
