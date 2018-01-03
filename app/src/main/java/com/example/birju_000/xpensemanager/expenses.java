package com.example.birju_000.xpensemanager;

import android.app.ActionBar;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.app.Activity;
import android.provider.CalendarContract;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import java.util.ArrayList;
import java.util.List;

public class expenses extends Activity
        implements  AdapterView.OnItemClickListener, View.OnClickListener{

    private ListView lstExpenses;
    private List<Expense> expenses = new ArrayList<>();
    private Button openCalendarBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses);

        lstExpenses = (ListView) findViewById(R.id.lstExpenses);
        openCalendarBtn = (Button) findViewById(R.id.openCalendarBtn);

        XpenseManagerDB db = new XpenseManagerDB(getApplicationContext());
        expenses = db.getExpenses();

        populateListView();
        lstExpenses.setOnItemClickListener(this);
        openCalendarBtn.setOnClickListener(this);
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

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        String item = ((TextView)view).getText().toString();

        Toast.makeText(getBaseContext(), item, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.openCalendarBtn)
        {
            Intent intent = new Intent(Intent.ACTION_INSERT);
            intent.setData(CalendarContract.Events.CONTENT_URI);
            startActivity(intent);
        }
    }
}
