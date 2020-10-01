package com.allana.crud_mvp_rx.presenter.list

import com.allana.crud_mvp_rx.model.data.DataItem

interface UserView {

    fun onSuccess(user: List<DataItem>?)
    fun onFailed()

    fun onSuccessDeleted()
    fun onFailedDeleted()

}