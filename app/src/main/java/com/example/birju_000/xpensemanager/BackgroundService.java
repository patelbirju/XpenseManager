package com.example.birju_000.xpensemanager;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

/**
 * Created by birju_000 on 03/01/2018.
 */

public class BackgroundService extends IntentService {
    public BackgroundService()
    {
        super("BackgroundService");
    }

    @Override
    protected void onHandleIntent(Intent workIntent) {
        String data = workIntent.getDataString();
    }
}
