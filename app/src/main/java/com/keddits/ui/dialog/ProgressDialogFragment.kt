package com.keddits.ui.dialog

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.keddits.R
import com.keddits.base.BaseDialogFragment

class ProgressDialogFragment : BaseDialogFragment() {
    override fun fragmentTag(): String = ProgressDialogFragment::class.java.simpleName

    private var cancelListener: DialogInterface.OnCancelListener? = null

    fun setOnCancelListener(onCancelListener: DialogInterface.OnCancelListener) {
        this.cancelListener = onCancelListener
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view : View = inflater.inflate(R.layout.dialog_material_progress, null)
        return view
    }

//    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
//        val dialog = ProgressDialog(context)
//        dialog.setMessage(getString(R.string.please_wait))
//        dialog.setCanceledOnTouchOutside(false)
//        dialog.isIndeterminate = true
//        return dialog
//    }

    override fun onCancel(dialog: DialogInterface?) {
        super.onCancel(dialog)
        if (cancelListener != null) {
            cancelListener!!.onCancel(dialog)
        }
    }

    companion object {

        fun newInstance(): ProgressDialogFragment {
            return ProgressDialogFragment()
        }
    }

}