package com.example.ilijaangeleski.phonebook.presenter;

import com.example.ilijaangeleski.phonebook.manager.UserDetailsManager;
import com.example.ilijaangeleski.phonebook.model.Location;
import com.example.ilijaangeleski.phonebook.view.UserDetailsView;

/**
 * Created by Ilija Angeleski on 11/20/2017.
 */

public class UserDetailsPresenter extends BaseAbstractPresenter<UserDetailsView> {
    private UserDetailsManager userDetailsManager;

    public UserDetailsPresenter(UserDetailsView view) {
        super(view);
        this.userDetailsManager = new UserDetailsManager();
    }

    public String getStreetInfo(Location l){
       return userDetailsManager.getStreetInfo(l);
    }
}
