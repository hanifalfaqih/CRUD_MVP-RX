package com.allana.crud_mvp_rx.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.allana.crud_mvp_rx.R
import com.allana.crud_mvp_rx.model.data.DataItem
import com.allana.crud_mvp_rx.presenter.input.InputPresenter
import com.allana.crud_mvp_rx.presenter.input.InputView
import kotlinx.android.synthetic.main.activity_input.*

class InputActivity : AppCompatActivity(), InputView {

    private lateinit var presenter: InputPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input)

        presenter = InputPresenter(this)

        val getDataUser = intent.getParcelableExtra<DataItem>("data")
        if (getDataUser != null) {
            edt_input_name.setText(getDataUser.mahasiswaNama)
            edt_input_address.setText(getDataUser.mahasiswaAlamat)
            edt_input_phone_number.setText(getDataUser.mahasiswaNohp)

            btn_save.text = getString(R.string.update)
        }

        if (btn_save.text == "Update") {
            btn_save.setOnClickListener {
                presenter.updateDataUser(getDataUser?.idMahasiswa, edt_input_name.text.toString(), edt_input_phone_number.text.toString(), edt_input_address.text.toString())
            }
        } else {
            btn_save.setOnClickListener {
                presenter.inputDataUser(edt_input_name, edt_input_address, edt_input_phone_number)
            }
        }

        btn_cancel.setOnClickListener {
            finish()
        }
    }

    override fun onSuccessUpdate() {
        Toast.makeText(applicationContext, "Data berhasil diupdate", Toast.LENGTH_SHORT).show()
    }

    override fun onFailedUpdate() {
        Toast.makeText(applicationContext, "Data gagal diupdate", Toast.LENGTH_SHORT).show()
    }

    override fun onSuccessInsert() {
        Toast.makeText(applicationContext, "Berhasil menambahkan data", Toast.LENGTH_SHORT).show()
    }

    override fun onFailedInsert() {
        Toast.makeText(applicationContext, "Gagal menambahkan data", Toast.LENGTH_SHORT).show()
    }
}