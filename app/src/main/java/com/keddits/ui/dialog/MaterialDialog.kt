package com.keddits.ui.dialog

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import com.keddits.R

/**
 * Created by marinaracu on 25.12.2017.
 */
class MaterialDialog(context: Context?) : AlertDialog(context) {
    override fun show() {
        super.show()
        setContentView(R.layout.dialog_material_progress)
        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }
}