package com.example.ilijaangeleski.phonebook.presenter;

import com.example.ilijaangeleski.phonebook.manager.UserDetailsManager;
import com.example.ilijaangeleski.phonebook.model.Location;
import com.example.ilijaangeleski.phonebook.view.UserDetailsView;

import java.lang.ref.WeakReference;

/**
 * Created by Ilija Angeleski on 11/20/2017.
 */

public class UserDetailsPresenter implements BasePresenter<UserDetailsView>{
    private WeakReference<UserDetailsView> userDetailsViewWeakReference;
    private UserDetailsManager userDetailsManager;

    public UserDetailsPresenter(UserDetailsView view) {
        this.userDetailsViewWeakReference = new WeakReference<>(view);
        this.userDetailsManager = new UserDetailsManager();
    }

    public String getStreetInfo(Location l){
       return userDetailsManager.getStreetInfo(l);
    }
    @Override
    public boolean isViewStillAlive() {
        return userDetailsViewWeakReference != null && userDetailsViewWeakReference.get() != null;
    }

    @Override
    public UserDetailsView getView() {
        return userDetailsViewWeakReference.get();
    }
}
