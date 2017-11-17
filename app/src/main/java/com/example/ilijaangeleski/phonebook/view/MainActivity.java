package com.example.ilijaangeleski.phonebook.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.ilijaangeleski.phonebook.R;
import com.example.ilijaangeleski.phonebook.adapter.RecyclerViewAdapter;
import com.example.ilijaangeleski.phonebook.model.User;
import com.example.ilijaangeleski.phonebook.presenter.MainPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainView{

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;
    MainPresenter presenter;
    LinearLayoutManager layoutManager;
    int currentPage=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initVariables();
        presenter.getUsers(currentPage);
    }
    public void initVariables(){
        presenter = new MainPresenter(this,this);
        recyclerViewAdapter = new RecyclerViewAdapter(presenter.getItems(), getApplicationContext(), R.layout.item);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    @Override
    public void checkInternetConnection() {
        presenter.checkInternetConnection();
    }

    @Override
    public void notifyChange() {
        recyclerViewAdapter.notifyDataSetChanged();
    }

}
