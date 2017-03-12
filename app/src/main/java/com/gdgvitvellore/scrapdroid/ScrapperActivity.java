package com.gdgvitvellore.scrapdroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.gdgvitvellore.scrapdroidlibrary.ScrapDroid;

public class ScrapperActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrapper);
        Log.v("response_view",new ScrapDroid().getResponse());
    }
}
