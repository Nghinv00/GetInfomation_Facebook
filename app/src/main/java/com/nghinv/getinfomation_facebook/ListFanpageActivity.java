package com.nghinv.getinfomation_facebook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListFanpageActivity extends AppCompatActivity {

    private static int count_fanpage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_fanpage);

        Intent inten = getIntent();
        String jsonFanpage= inten.getStringExtra("LIKES");
        JSONArray fanpageList;
        ArrayList<String> fanpages = new ArrayList<String>();

        try{
            fanpageList = new JSONArray(jsonFanpage);
            count_fanpage = fanpageList.length();
            for( int i = 0; i < fanpageList.length(); i++){
                fanpages.add(fanpageList.getJSONObject(i).getString("name"));
            }
        }
        catch (JSONException e){
            e.printStackTrace();
        }
        TextView txtSofanpage = (TextView) findViewById(R.id.txt1);
        txtSofanpage.setText("Danh sách fanpage " + String.valueOf(count_fanpage));

        ArrayAdapter adapter = new ArrayAdapter<String>( this, android.R.layout.simple_list_item_1, fanpages);
        ListView lvFanpage = (ListView) findViewById(R.id.lvFanpage);
        lvFanpage.setAdapter(adapter);
    }
}
