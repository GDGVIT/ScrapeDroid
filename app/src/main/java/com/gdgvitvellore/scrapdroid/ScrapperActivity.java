package com.gdgvitvellore.scrapdroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.gdgvitvellore.scrapdroidlibrary.ScrapDroid;

import java.util.HashMap;

public class ScrapperActivity extends AppCompatActivity {


    HashMap<String,String> params=new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrapper);
      //  params.put("para","link")

        params.put("name","{'element':'a[class=author waves-effect waves-dark grey lighten-4]','attr':'href'}");
        new ScrapDroid().getTagsAsList(params);
    }
}
