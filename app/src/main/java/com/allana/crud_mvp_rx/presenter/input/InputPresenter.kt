package com.allana.crud_mvp_rx.presenter.input

import android.widget.EditText
import com.allana.crud_mvp_rx.network.UserNetwork
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_input.*

class InputPresenter(private val inputView: InputView) {

    fun updateDataUser(id_mahasiswa: String?, mahasiswa_nama: String?, mahasiswa_nohp: String?, mahasiswa_alamat: String?) {
        UserNetwork.getService().updateData(id_mahasiswa ?: "", mahasiswa_nama ?: "", mahasiswa_nohp ?: "", mahasiswa_alamat ?: "")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({response ->
                if (response.isSuccess!!){
                    inputView.onSuccessUpdate()
                }
            }, {error ->
                inputView.onFailedUpdate()
            })
    }

    fun insertDataUser(mahasiswa_nama: String?, mahasiswa_nohp: String?, mahasiswa_alamat: String?) {
        UserNetwork.getService().insertData(mahasiswa_nama ?: "", mahasiswa_nohp ?: "", mahasiswa_alamat ?: "")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({response ->
                if (response.isSuccess!!){
                    inputView.onSuccessInsert()
                }
            }, {error ->
                inputView.onFailedInsert()
            })
    }

    fun inputDataUser(edt_nama: EditText, edt_alamat: EditText, edt_nohp: EditText) {
        val edtName = edt_nama.text.toString()
        val edtPhoneNumber = edt_nohp.text.toString().trim()
        val edtAddress = edt_alamat.text.toString()

        var isEmptyField = false
        when{
            edtName.isEmpty() -> {
                isEmptyField = true
                edt_nama.error = "Nama harus diisi!"
            }
            edtAddress.isEmpty() -> {
                isEmptyField = true
                edt_alamat.error = "Alamat harus diisi!"
            }
            edtPhoneNumber.isEmpty() -> {
                isEmptyField = true
                edt_nohp.error = "Nomor handphone harus diisi!"
            }
        }

        if (!isEmptyField){
            insertDataUser(edtName, edtPhoneNumber, edtAddress)
        }
    }

}