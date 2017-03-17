package com.gdgvitvellore.scrapdroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.gdgvitvellore.scrapdroidlibrary.ScrapDroid;

import java.util.HashMap;

public class ScrapperActivity extends AppCompatActivity {


    HashMap<Object,Object> params=new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrapper);

        params.put("para","<img src=");
        new ScrapDroid().getTagsAsList(params);
    }
}
