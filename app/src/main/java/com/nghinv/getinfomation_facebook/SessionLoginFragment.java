package com.nghinv.getinfomation_facebook;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.telecom.Call;
import android.view.View;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.login.widget.LoginButton;
import android.service.textservice.SpellCheckerService.Session;

import java.util.Arrays;

/**
 * Created by NghiNV on 31/03/2017.
 */

public class SessionLoginFragment  extends Fragment {

    private static String userID;
    private static final String URL_PREFIX_FRIENDS = "https://graph.facebook.com/me/friends?access_token=";
    String profileImgUrl = "https://graph.facebook.com/"+ userID + "/picture?type=normal";
    TextView txtInfo;
    LoginButton btnLogin;
    CallbackManager callbackManager;
    FacebookCallback facebookCallback;
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        btnLogin.setReadPermissions(Arrays.asList(
                "public_profile", "email", "user_birthday", "user_friends"));
        btnLogin.registerCallback(callbackManager, facebookCallback);
    }




    }
