package com.reddittask.android.ui.activity.main;

import com.reddittask.android.model.GameData;
import com.reddittask.android.ui.activity.base.MvpView;

/**
 * Created by marinaracu on 30.11.2017.
 */

public interface MainView extends MvpView {

    void openDetailGame(int position, String url);

    void displayGameData(GameData gameData);

    void showErrorMessage();
}
