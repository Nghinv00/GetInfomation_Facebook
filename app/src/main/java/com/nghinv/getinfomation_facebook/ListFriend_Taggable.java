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

public class ListFriend_Taggable extends AppCompatActivity {
    private static int total_count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_friend__taggable);

        Intent intent = getIntent();
        String data = intent.getStringExtra("JSONDATA");
        String summary= intent.getStringExtra("PAGING");    // dữ liệu ở thẻ Pagging
        JSONArray friends_Tag;
        JSONObject object;
        ArrayList<String> friends = new ArrayList<String>();

        try{
            friends_Tag = new JSONArray(data);
            total_count = friends_Tag.length();
            for( int i = 0; i < friends_Tag.length(); i++){
                friends.add(friends_Tag.getJSONObject(i).getString("name"));
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, friends);
        ListView lvInfo = (ListView) findViewById(R.id.lvlistTag);
        lvInfo.setAdapter(adapter);
    }
}
