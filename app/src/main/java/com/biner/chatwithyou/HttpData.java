package com.biner.chatwithyou;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Function
 * <p/>
 * Created by Biner on 2016/3/7.
 */
public class HttpData extends AsyncTask<String,Void,String>{
    private String address;
    private HttpGetDataListener listener;

    public HttpData(String address,HttpGetDataListener listener){
        this.address=address;
        this.listener=listener;
    }

    @Override
    protected String doInBackground(String... params) {
        HttpURLConnection connection = null;
        try {
            URL url=new URL(address);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(8 * 1000);
            connection.setReadTimeout(8*1000);
            if (connection.getResponseCode()!=200){
                return null;
            }
            BufferedReader reader =new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            StringBuilder response=new StringBuilder();
            String line;
            while ((line=reader.readLine())!=null){
                response.append(line);
            }
            return response.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection!=null){
                connection.disconnect();
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        listener.getDataUrl(s);
        super.onPostExecute(s);
    }
}
