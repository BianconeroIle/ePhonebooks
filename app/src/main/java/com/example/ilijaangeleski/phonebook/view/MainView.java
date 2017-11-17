package com.example.ilijaangeleski.phonebook.view;

/**
 * Created by Ilija Angeleski on 11/17/2017.
 */

public interface MainView {
    void notifyChange();
    boolean checkInternetConnection();
    void noInternetConnection();
    void errorGettingUsers();
}
