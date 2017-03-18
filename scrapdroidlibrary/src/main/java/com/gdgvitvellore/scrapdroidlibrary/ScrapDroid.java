package com.gdgvitvellore.scrapdroidlibrary;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

/**
 * Created by ramkishorevs on 12/03/17.
 */

public class ScrapDroid {


    public String getResponse()
    {
        String response = null;

        try {
          response =new FetchResponse().execute().get();

        }
        catch (InterruptedException e) {

           e.printStackTrace();

        }
        catch (ExecutionException e) {

            e.printStackTrace();
        }

        return response;

    }


    public void getTagsAsList(HashMap<String,String> params)
    {

        String response=getResponse();


        Document document= Jsoup.parse(response);


        for (Map.Entry<String,String> e : params.entrySet()) {

            
            String jsonParam=e.getValue();
            String element = null,attr = null;
                    
            try {
               
                JSONObject jsonObject=new JSONObject(jsonParam);
                element=jsonObject.getString("element");
                attr=jsonObject.getString("attr");
            
            } catch (JSONException e1) {
                e1.printStackTrace();
            }

            Elements links = document.select(element);

            Log.v("hu",links.get(1).attr(attr));

        }





    }



    private class FetchResponse extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            // These two need to be declared outside the try/catch
            // so that they can be closed in the finally block.
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;

            // Will contain the raw JSON response as a string.
            String response = null;

            try {

                URL url = new URL("http://blog.gdgvitvellore.com/");

                // Create the request to OpenWeatherMap, and open the connection
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                // Read the input stream into a String
                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    // Nothing to do.
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {

                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {
                    // Stream was empty.  No point in parsing.
                    return null;
                }
                response = buffer.toString();


                return response;
            } catch (IOException e) {

                e.printStackTrace();

                return null;
            } finally{
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                    }
                }
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
          //  Log.v("response",s);
        }
    }
}

