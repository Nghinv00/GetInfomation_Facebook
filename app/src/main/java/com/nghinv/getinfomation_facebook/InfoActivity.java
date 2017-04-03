package com.nghinv.getinfomation_facebook;

import android.content.Intent;
import android.media.session.MediaSession;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphRequestAsyncTask;
import com.facebook.GraphRequestBatch;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.Profile;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.login.widget.ProfilePictureView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

public class InfoActivity extends AppCompatActivity {

    private ProfilePictureView profilePictureView;
    private LinearLayout linearLayout;

    TextView txtInfo;
    Button btnListFriend, btnListFanpage;
    LoginButton btnLogin;
    private CallbackManager callbackManager;
    String id, birthday, firstname, gender, lastname, link, location, id_location, name_location, locale, name, timezone, updatetime, verified, email;
    private  static String info;
    public static String thongtin;
    public static GraphResponse response1, response2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        facebookSDKInitialize();
        setContentView(R.layout.activity_info);

        profilePictureView = (ProfilePictureView) findViewById(R.id.profilePictureView);
        btnLogin = (LoginButton) findViewById(R.id.btnLogin);
        txtInfo = (TextView) findViewById(R.id.txtInfo);
        linearLayout = (LinearLayout) findViewById(R.id.ln1);

        btnLogin.setReadPermissions(Arrays.asList(
                "public_profile", "email", "user_birthday", "user_friends","user_likes"));

        btnLogin.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(final LoginResult loginResult) {
                AccessToken accessToken = loginResult.getAccessToken();
                AccessToken token = AccessToken.getCurrentAccessToken();


                /**
                 * Danh sách bạn bè theo : taggable_friends
                 */
                new GraphRequest(
                        loginResult.getAccessToken(),
                        "me/taggable_friends",
                        null,
                        HttpMethod.GET,
                        new GraphRequest.Callback() {
                            public void onCompleted(GraphResponse response) {
                                Intent intent = new Intent(InfoActivity.this , ListFriend_Taggable.class);
                                try {
                                    JSONArray rawName = response.getJSONObject().getJSONArray("data");
                                    JSONObject rawName1 = response.getJSONObject().getJSONObject("paging");

                                    intent.putExtra("JSONDATA", rawName.toString());
                                    intent.putExtra("PAGING", rawName1.toString());
                                    startActivity(intent);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                ).executeAsync();

                /**
                 * Danh sách fanpage đã like:
                 */
             /*   GraphRequestAsyncTask graphRequestAsyncTask = new GraphRequest(
                        loginResult.getAccessToken(),
                        "/me/likes",
                        null,
                        HttpMethod.GET,
                        new GraphRequest.Callback() {
                            public void onCompleted(GraphResponse response) {
                                Intent intent = new Intent(InfoActivity.this,ListFanpageActivity.class);
                                try {
                                    response1 = response;
                                    JSONArray rawName = response.getJSONObject().getJSONArray("data");
                                    intent.putExtra("LIKES", rawName.toString());
                                    startActivity(intent);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                ).executeAsync();
*/

                /**
                 * Cách 3 : láy danh sách bạn bè friend :
                 */
               /* GraphRequestAsyncTask graphRequestAsyncTask1 = new GraphRequest(
                        loginResult.getAccessToken(),
                        // "/me/friends",
                        "me/friends",
                        null,
                        HttpMethod.GET,
                        new GraphRequest.Callback() {
                            public void onCompleted(GraphResponse response) {
                                Intent intent = new Intent(InfoActivity.this,ListFriendActivity.class);
                                try {
                                    JSONArray rawName = response.getJSONObject().getJSONArray("data");
                                    JSONObject rawName1 = response.getJSONObject().getJSONObject("summary");

                                    intent.putExtra("JSONDATA", rawName.toString());
                                    intent.putExtra("SUMMARY", rawName1.toString());
                                    startActivity(intent);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                ).executeAsync();*/

                 /**
                 * Cách 1 : Sử dụng phiên bản < 2.5 để lấy danh sách bạn bè nhưng chỉ lấy được tổng số bạn bè
                 */
//                GraphRequest graphRequest = GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {
//                    @Override
//                    public void onCompleted(JSONObject jsonObject, GraphResponse graphResponse) {
//                        try {
//                            JSONArray rawName = graphResponse.getJSONObject().getJSONArray("data");
//                            JSONArray jsonArrayFriends = jsonObject.getJSONObject("friends").getJSONArray("data");
//                            JSONObject friendlistObject = jsonArrayFriends.getJSONObject(0);
//                            String friendListID = friendlistObject.getString("id");
//                            myNewGraphReq(friendListID);
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                });
//                Bundle param = new Bundle();
////                param.putString("fields", "friendlist", "members");
//                param.putString("fields", "friends");
//                graphRequest.setParameters(param);
//                graphRequest.executeAsync();

                 /**
                 * Lấy toàn bộ thông tin của người dùng ứng dụng
                 */
  /*                     GraphRequest request = GraphRequest.newMeRequest( accessToken , new GraphRequest.GraphJSONObjectCallback(){
                            @Override
                            public void onCompleted( JSONObject object, GraphResponse reponse){
                                JSONArray arrFriend;
                                AccessToken accessToken1 = loginResult.getAccessToken();
                                try{
                                    id = object.getString("id");
                                    birthday = object.getString("birthday");
                                    gender = object.getString("gender");
                                    link = object.getString("link");
                                    locale = object.getString("locale");
                                    name = object.getString("name");
                                    email = object.getString("email");
                                    JSONObject location = object.getJSONObject("location");
                                    String location_id = location.getString("id");
                                    String location_name = location.getString("name");
                                    profilePictureView.setPresetSize(ProfilePictureView.NORMAL);
                                    profilePictureView.setProfileId(object.getString("id"));
                                    linearLayout.setVisibility(View.VISIBLE);
                                }
                                catch (JSONException e){
                                    e.printStackTrace();
                                }
                                txtInfo.setText(id + "\n" + name + "\n" + link + "\n" + birthday +"\n"+ gender + "\n"+ email
                                + "\n" + locale + "\n"  );
                            }
                        });
                Bundle param = new Bundle();
                param.putString("fields", "id,name,link,birthday,gender,email,locale");
//                param.putString("fields", "friendlist");
                request.setParameters(param);
                request.executeAsync();
*/
                /**
                 * Cách 2 : sử dụng phiên bản mới nhất để lấy DS bạn bè
                 *  Giá trị trả về :  {"summary":{"total_count":359 },"data":[]}
                 */
                /*
                Log.e("TAG", "onSuccess");
                GraphRequestBatch batch = new GraphRequestBatch(
                        GraphRequest.newMyFriendsRequest( loginResult.getAccessToken(),   new GraphRequest.GraphJSONArrayCallback() {
                            @Override
                            public void onCompleted(JSONArray jsonArray, GraphResponse response) {
                                Log.i("TAG", "onCompleted: jsonArray "+ jsonArray);
                                Log.i("TAG", "onCompleted: response " + response);
                                Intent intent = new Intent(MainActivity.this,  ListFriendActivity.class);
                                try {
                                    JSONArray listFriend = response.getJSONObject().getJSONArray("data");
                                    intent.putExtra("friend", listFriend.toString());
                                    startActivity(intent);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }),
                        GraphRequest.newMeRequest( loginResult.getAccessToken(),  new GraphRequest.GraphJSONObjectCallback() {
                                    @Override
                                    public void onCompleted(JSONObject jsonObject, GraphResponse response) {
                                        try {
                                            id = jsonObject.getString("id");

                                        } catch (JSONException j){
                                            j.printStackTrace();
                                        }
                                        info = id;
                                    }
                                })

                );
                batch.addCallback(new GraphRequestBatch.Callback() {
                    @Override
                    public void onBatchCompleted(GraphRequestBatch batch) {
                        txtInfo.setText("Completed" + info);
                    }
                });
                batch.executeAsync();
                */
            }

            @Override
            public void onCancel() {
                txtInfo.setText("Login Cancel");
            }
            @Override
            public void onError(FacebookException error) {
               Log.e(error.getMessage() + "", "aaa");
                txtInfo.setText("Login atemp failed");
            }
        });
    }

    private void facebookSDKInitialize() {
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void myNewGraphReq(String friendListID) {
        final String graphPath = "/"+friendListID+"/members/";
        AccessToken token = AccessToken.getCurrentAccessToken();
        GraphRequest graphRequest = new GraphRequest(token, graphPath, null, HttpMethod.GET, new GraphRequest.Callback() {
            @Override
            public void onCompleted(GraphResponse graphResponse) {
                JSONObject object = graphResponse.getJSONObject();
                try {
                    JSONArray arrayOfUsersInFriendList= object.getJSONArray("data");
                    /* Do something with the user list */
                    /* ex: get first user in list, "name" */
                    JSONObject user = arrayOfUsersInFriendList.getJSONObject(0);
                    String usersName = user.getString("name");
//                    String id = user.getString("id");

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        Bundle param = new Bundle();
        param.putString("fields", "name");
        graphRequest.setParameters(param);
        graphRequest.executeAsync();
    }
    @Override
    protected void onResume() {
        super.onResume();

        // Logs 'install' and 'app activate' App Events.
        AppEventsLogger.activateApp(this);
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Logs 'app deactivate' App Event.
        AppEventsLogger.deactivateApp(this);
    }
}