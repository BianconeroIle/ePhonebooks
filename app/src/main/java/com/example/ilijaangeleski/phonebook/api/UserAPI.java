package com.example.ilijaangeleski.phonebook.api;

import com.example.ilijaangeleski.phonebook.model.ResponseDTO;


import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by Ilija Angeleski on 12/13/2016.
 */

public interface UserAPI {

    @GET("/")
    void getUsers(@Query("seed") String seed, @Query("page") int page, @Query("results") int results, Callback<ResponseDTO> callback);

}
