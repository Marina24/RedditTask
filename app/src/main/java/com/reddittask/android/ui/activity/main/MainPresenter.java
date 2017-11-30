package com.reddittask.android.ui.activity.main;

import com.reddittask.android.model.GameData;
import com.reddittask.android.ui.activity.base.MvpPresenter;
import com.reddittask.android.ui.activity.base.MvpView;

/**
 * Created by marinaracu on 30.11.2017.
 */

public interface MainPresenter<V extends MainView> extends MvpPresenter<V>{

    void onItemNewsClicked(int position, String url);

    void loadNextData(int dataCountLimit);

}
