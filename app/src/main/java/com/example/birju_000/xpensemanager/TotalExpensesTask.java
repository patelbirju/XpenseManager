package com.example.birju_000.xpensemanager;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;

/**
 * Created by birju_000 on 03/01/2018.
 */

public class TotalExpensesTask extends AsyncTask<Void,Integer,String> {
    Context context;
    TextView textView;
    double total = 0;

    TotalExpensesTask(Context context, TextView textView){
        this.context = context;
        this.textView = textView;
    }

    @Override
    protected String doInBackground(Void...params) {
        int i = 0;

        XpenseManagerDB db = new XpenseManagerDB(context);
        total = db.getTotalExpenses();
        synchronized ((this))
        {
            while (i < 0)
            {
                try {
                    wait(300);
                    i++;
                    //publishProgress(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return "Completed";
    }

    @Override
    protected void onPreExecute() {

    }

    @Override
    protected void onPostExecute(String result) {
        textView.setText("Total expenses - " + total);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
    }
}
