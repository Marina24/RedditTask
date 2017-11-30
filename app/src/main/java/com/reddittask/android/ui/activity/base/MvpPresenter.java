package com.reddittask.android.ui.activity.base;

/**
 * Created by marinaracu on 30.11.2017.
 */

public interface MvpPresenter<V extends MvpView> {

    void onAttach(V mvpView);

    void onDetach();
}
