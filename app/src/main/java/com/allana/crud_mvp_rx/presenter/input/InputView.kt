package com.allana.crud_mvp_rx.presenter.input

interface InputView {

    fun onSuccessUpdate()
    fun onFailedUpdate()

    fun onSuccessInsert()
    fun onFailedInsert()
}