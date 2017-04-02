package com.nghinv.getinfomation_facebook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphRequestBatch;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.login.widget.ProfilePictureView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.nghinv.getinfomation_facebook.R.id.profilePictureView;
import static com.nghinv.getinfomation_facebook.R.id.txtInfo12;

public class ListFriendActivity extends AppCompatActivity {

    private static int total_count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(ListFriendActivity.this);
        setContentView(R.layout.activity_list_friend);
        TextView txtInfo12 = (TextView) findViewById(R.id.txtInfo12);

        Intent intent = getIntent();
        String data = intent.getStringExtra("JSONDATA");
        String summary= intent.getStringExtra("SUMMARY");
        JSONArray friendslist;
        JSONObject object;
        ArrayList<String> friends = new ArrayList<String>();

        try{
            object = new JSONObject(summary);
            total_count = object.getInt("total_count");
            friendslist = new JSONArray(data);
            for( int i = 0; i < friendslist.length(); i++){
                friends.add(friendslist.getJSONObject(i).getString("name"));
            }
        }
            catch (JSONException e) {
                e.printStackTrace();
            }
        TextView txtCount = (TextView) findViewById(R.id.txtInfo12);
        txtCount.setText( "Danh sách bạn bè trong danh bạ "+ String.valueOf(total_count) + "\n"+ "Danh Sách bạn bè sử dụng cùng ứng dụng " );
        ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, friends);
        ListView lvInfo = (ListView) findViewById(R.id.lvListFriend);
        lvInfo.setAdapter(adapter);
    }
}
