package com.example.ilijaangeleski.phonebook.manager;

import android.util.Log;

import com.example.ilijaangeleski.phonebook.api.ApiConstants;
import com.example.ilijaangeleski.phonebook.api.UserAPI;
import com.example.ilijaangeleski.phonebook.model.ResponseDTO;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

import static android.content.ContentValues.TAG;

/**
 * Created by Ilija Angeleski on 11/17/2017.
 */

public class MainManager {
    UserAPI api;

    public MainManager() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(ApiConstants.URL)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();

        api = restAdapter.create(UserAPI.class);
    }

    public void getUsers(int page, final GetUsersCallback callback) {
        api.getUsers("egym", page, 10, new Callback<ResponseDTO>() {
            @Override
            public void success(ResponseDTO responseDTO, Response response) {
                Log.d(TAG, "successful getting users from server" + responseDTO);
                callback.success(responseDTO);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d(TAG, "error getting users from server" + error);
                callback.failure(error);
            }
        });
    }

    public interface GetUsersCallback {
        void success(ResponseDTO responseDTO);
        void failure(RetrofitError error);
    }
}
