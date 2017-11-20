package com.example.ilijaangeleski.phonebook.view;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.ilijaangeleski.phonebook.R;
import com.example.ilijaangeleski.phonebook.adapter.RecyclerViewAdapter;
import com.example.ilijaangeleski.phonebook.model.User;
import com.example.ilijaangeleski.phonebook.presenter.MainPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainView {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;
    MainPresenter presenter;
    LinearLayoutManager layoutManager;
    int currentPage = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initVariables();
        initListeners();

        presenter.getUsers(currentPage);
    }

    public void initVariables() {
        presenter = new MainPresenter(this);
        recyclerViewAdapter = new RecyclerViewAdapter(presenter.getItems(), getApplicationContext(), R.layout.item);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    public void initListeners() {
        recyclerViewAdapter.setOnUserItemClick(new RecyclerViewAdapter.OnUserItemClick() {
            @Override
            public void onUserClick(User user, ImageView imageView) {
                Log.d("OnUserClick", "onUserClick :" + user);
                Intent intent = new Intent(MainActivity.this, UserDetailsActivity.class);
                intent.putExtra("clickedUser", user);
                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation(MainActivity.this, imageView, "profile");
                startActivity(intent, options.toBundle());
            }
        });
    }

    @Override
    public void notifyChange() {
        recyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean checkInternetConnection() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            return true;
        }

        return false;
    }

    @Override
    public void noInternetConnection() {
        Toast.makeText(this, "No internet connection !", Toast.LENGTH_LONG).show();
    }

    @Override
    public void errorGettingUsers() {
        Toast.makeText(this, "Error getting users !", Toast.LENGTH_LONG).show();

    }
}
