package com.reddittask.android.ui.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;

import com.reddittask.android.R;

/**
 * Created by marinaracu on 30.11.2017.
 */

public class MaterialDialog extends AlertDialog {

    public MaterialDialog(@NonNull Context context) {
        super(context);
    }

    protected MaterialDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }

    protected MaterialDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    public void show() {
        super.show();
        setContentView(R.layout.dialog_material_progress);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }
}
