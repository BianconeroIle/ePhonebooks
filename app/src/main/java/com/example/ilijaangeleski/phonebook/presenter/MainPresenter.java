package com.example.ilijaangeleski.phonebook.presenter;

import com.example.ilijaangeleski.phonebook.manager.MainManager;
import com.example.ilijaangeleski.phonebook.model.ResponseDTO;
import com.example.ilijaangeleski.phonebook.model.User;
import com.example.ilijaangeleski.phonebook.view.MainView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import retrofit.RetrofitError;

/**
 * Created by Ilija Angeleski on 11/17/2017.
 */

public class MainPresenter implements BasePresenter<MainView> {
    private WeakReference<MainView> viewWeakReference;
    private MainManager mainManager;
    private List<User> items = new ArrayList<>();

    public MainPresenter(MainView view) {
        this.viewWeakReference = new WeakReference<>(view);
        this.mainManager = new MainManager();
    }

    public void getUsers(int page) {
        if (isViewStillAlive() && !getView().checkInternetConnection()) {
            getView().noInternetConnection();

            return;
        }
        mainManager.getUsers(page, new MainManager.GetUsersCallback() {
            @Override
            public void success(ResponseDTO responseDTO) {
                items.addAll(responseDTO.getResults());
                if (isViewStillAlive()) {
                    getView().notifyChange();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                if (isViewStillAlive()) {
                    getView().errorGettingUsers();
                }
            }
        });
    }

    public List<User> getItems() {
        return items;
    }

    @Override
    public boolean isViewStillAlive() {
        return viewWeakReference != null && viewWeakReference.get() != null;
    }

    @Override
    public MainView getView() {
        return viewWeakReference.get();
    }
}
