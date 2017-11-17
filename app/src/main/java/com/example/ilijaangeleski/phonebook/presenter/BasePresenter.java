package com.example.ilijaangeleski.phonebook.presenter;

/**
 * Created by Ilija Angeleski on 11/17/2017.
 */

public interface BasePresenter<T> {
    boolean isViewStillAlive();
    T getView();
}
