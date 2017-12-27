package com.keddits.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.keddits.ui.dialog.MaterialDialog
import com.keddits.ui.dialog.ProgressDialogFragment

/**
 * Created by marinaracu on 25.12.2017.
 */
abstract class BaseActivity<out T : BaseMvpContract.BasePresenter> : AppCompatActivity(),
        BaseMvpContract.BaseView {

    private var progressDialog: MaterialDialog? = null
    abstract protected val presenter: T
    abstract protected val layoutResourceId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutResourceId)
        presenter.onAttach(this)
    }

    override fun showProgress() {
        progressDialog.let {
            progressDialog = MaterialDialog(this)
        }

        if (!progressDialog?.isShowing!!){
            progressDialog?.show()
        }
    }

    override fun hideProgress() {
        if (progressDialog != null && progressDialog!!.isShowing){
            progressDialog?.dismiss()
        }
    }

    override fun showError(strResId: Int) {

    }

    override fun showError(error: String?) {

    }

    override fun showMessage(srtResId: Int) {

    }

    override fun showMessage(message: String?) {

    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDetach()
    }
}