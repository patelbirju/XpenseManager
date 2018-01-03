package com.example.birju_000.xpensemanager;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by birju_000 on 03/01/2018.
 */

public class GetIconTask extends AsyncTask<Void,Integer, String> {

    Context context;
    ImageView imageView;
    String src;
    Bitmap myIcon;

    GetIconTask(Context context, ImageView imageView){
        this.context = context;
        this.imageView = imageView;
        this.src = "http://icons.iconarchive.com/icons/custom-icon-design/pretty-office-11/72/cash-icon.png";
    }

    @Override
    protected String doInBackground(Void...params) {
        int i = 0;

        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setDoInput(true);
            connection.connect();

            InputStream ip = connection.getInputStream();
            myIcon = BitmapFactory.decodeStream(ip);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        synchronized ((this))
        {
            while (i < 0)
            {
                try {
                    wait(300);
                    i++;

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return "Downloaded";
    }

    @Override
    protected void onPreExecute() {

    }

    @Override
    protected void onPostExecute(String result) {
        imageView.setImageBitmap(myIcon);
    }

}
