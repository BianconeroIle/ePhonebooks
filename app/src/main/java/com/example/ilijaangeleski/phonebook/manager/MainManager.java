package com.example.ilijaangeleski.phonebook.manager;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.example.ilijaangeleski.phonebook.api.ApiConstants;
import com.example.ilijaangeleski.phonebook.api.UserAPI;
import com.example.ilijaangeleski.phonebook.model.ResponseDTO;
import com.example.ilijaangeleski.phonebook.model.User;
import com.example.ilijaangeleski.phonebook.view.MainView;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

import static android.content.ContentValues.TAG;

/**
 * Created by Ilija Angeleski on 11/17/2017.
 */

public class MainManager {

    MainView view;
    public static final List<User> items = new ArrayList<>();

    public boolean checkForInternetConnection(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            return true;
        } else
            return false;
    }


    public List<User> getItems() {
        return items;
    }
}
