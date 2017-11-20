package com.example.ilijaangeleski.phonebook.presenter;

import java.lang.ref.WeakReference;

public class BaseAbstractPresenter<T> implements BasePresenter<T> {

    private WeakReference<T> view;

    public BaseAbstractPresenter(T view){
        this.view = new WeakReference<>(view);
    }

    @Override
    public boolean isViewStillAlive() {
        return view != null && view.get() != null;
    }

    @Override
    public T getView() {
        return view.get();
    }
}
