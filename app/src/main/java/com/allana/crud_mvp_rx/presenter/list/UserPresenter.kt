package com.allana.crud_mvp_rx.presenter.list

import android.content.Intent
import androidx.core.content.ContextCompat.startActivity
import com.allana.crud_mvp_rx.adapter.UserAdapter
import com.allana.crud_mvp_rx.model.data.DataItem
import com.allana.crud_mvp_rx.network.UserNetwork
import com.allana.crud_mvp_rx.view.InputActivity
import com.allana.crud_mvp_rx.view.MainActivity
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class UserPresenter(private val userView: UserView) {

    fun getDataUser() {
        UserNetwork.getService().getData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({response ->
                if (response.isSuccess!!){
                    userView.onSuccess(response.data)
                }
            }, {error ->
                userView.onFailed()
            })
    }

    fun getDeleteUser(id_mahasiswa: String?) {
        UserNetwork.getService().deleteData(id_mahasiswa?: "")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                userView.onSuccessDeleted()
                getDataUser()
            }, {error ->
                userView.onFailedDeleted()
            })
    }

}