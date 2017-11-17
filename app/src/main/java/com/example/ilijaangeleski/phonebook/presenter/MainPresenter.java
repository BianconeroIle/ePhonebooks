package com.example.ilijaangeleski.phonebook.presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.ilijaangeleski.phonebook.api.ApiConstants;
import com.example.ilijaangeleski.phonebook.api.UserAPI;
import com.example.ilijaangeleski.phonebook.manager.MainManager;
import com.example.ilijaangeleski.phonebook.model.ResponseDTO;
import com.example.ilijaangeleski.phonebook.model.User;
import com.example.ilijaangeleski.phonebook.view.MainView;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

import static android.content.ContentValues.TAG;
import static com.example.ilijaangeleski.phonebook.manager.MainManager.items;

/**
 * Created by Ilija Angeleski on 11/17/2017.
 */

public class MainPresenter {
    MainView view;
    MainManager mainManager;
    Context context;
    UserAPI api;


    public MainPresenter(MainView view,Context context) {
        this.view = view;
        this.context=context;
        mainManager = new MainManager();
    }

    public void getUsers(int page) {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(ApiConstants.URL)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();

        api = restAdapter.create(UserAPI.class);
        api.getUsers("egym", page, 10, new Callback<ResponseDTO>() {
            @Override
            public void success(ResponseDTO responseDTO, Response response) {
                Log.d(TAG, "successful getting users from server" + responseDTO);
                items.addAll(responseDTO.getResults());
                view.notifyChange();
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d(TAG, "error getting users from server" + error);
            }
        });
    }
    public void checkInternetConnection() {
        if (mainManager.checkForInternetConnection(context)) {
            Log.d(TAG, "get users from server!");
        }else{
            Toast.makeText(context,"No users found",Toast.LENGTH_LONG).show();
        }
    }
    public List<User> getItems() {
        return items;
    }
}
