package com.biner.chatwithyou;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, HttpGetDataListener {
    private List<ListData> lists;
    private EditText sendtext;
    private ListAdapter adapter;
    private double oldTime = 0;
    private ListView lv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        lv = (ListView) findViewById(R.id.lv);
        sendtext = (EditText) findViewById(R.id.et_send);
        Button send_btn = (Button) findViewById(R.id.bt_send);
        lists = new ArrayList<>();
        send_btn.setOnClickListener(this);
        adapter = new ListAdapter(lists, this);
        lv.setAdapter(adapter);
        ListData listData;
        listData = new ListData(getRandomWelcomeTips(), 1,
                getTime());
        lists.add(listData);
    }

    private String getRandomWelcomeTips() {
        String welcome_tip = null;
        String[] welcome_array = this.getResources()
                .getStringArray(R.array.welcome_tips);
        int index = (int) (Math.random() * (welcome_array.length - 1));
        welcome_tip = welcome_array[index];
        return welcome_tip;
    }

    @Override
    public void getDataUrl(String data) {
        // System.out.println(data);
        parseText(data);
    }

    public void parseText(String str) {
        try {
            JSONObject jb = new JSONObject(str);

            ListData listData;
            listData = new ListData(jb.getString("text"), 1,
                    getTime());

            lists.add(listData);
            lv.setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
            adapter.notifyDataSetChanged();
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, InputMethodManager.HIDE_NOT_ALWAYS);
        }
        getTime();
        String content_str = sendtext.getText().toString();
        sendtext.setText("");
        String dropk = content_str.replace(" ", "");
        String droph = dropk.replace("\n", "");
        ListData listData;
        listData = new ListData(content_str, 0, getTime());
        lists.add(listData);
        if (lists.size() > 30) {
            for (int i = 0; i < lists.size(); i++) {
                lists.remove(i);
            }
        }
        adapter.notifyDataSetChanged();

        HttpData httpData = (HttpData) new HttpData(
                "http://www.tuling123.com/openapi/api?key=ba1d4f0c234f19313d648a062dc4b2e4&info="
                        + droph, this).execute();

    }

    private String getTime() {
        double currentTime = System.currentTimeMillis();
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        Date curDate = new Date();
        String str = format.format(curDate);
        if (currentTime - oldTime >= 500) {
            oldTime = currentTime;
            return str;
        } else {
            return "";
        }

    }
}
