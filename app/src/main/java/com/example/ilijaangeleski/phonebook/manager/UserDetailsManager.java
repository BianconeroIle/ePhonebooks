package com.example.ilijaangeleski.phonebook.manager;

import com.example.ilijaangeleski.phonebook.api.ApiConstants;
import com.example.ilijaangeleski.phonebook.api.UserAPI;
import com.example.ilijaangeleski.phonebook.model.Location;

import retrofit.RestAdapter;

/**
 * Created by Ilija Angeleski on 11/20/2017.
 */

public class UserDetailsManager {
    private UserAPI api;

    public UserDetailsManager(){
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(ApiConstants.URL)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();

        api = restAdapter.create(UserAPI.class);
    }

    public String getStreetInfo(Location l) {
        StringBuilder b = new StringBuilder();
        b.append(l.getStreet());

        if (l.getCity() != null) {
            b.append(", " + l.getCity());
        }

        if (l.getState() != null) {
            b.append(", " + l.getState());
        }
        return b.toString();
    }
}
