package com.allana.crud_mvp_rx.view

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.allana.crud_mvp_rx.R
import com.allana.crud_mvp_rx.adapter.UserAdapter
import com.allana.crud_mvp_rx.model.data.DataItem
import com.allana.crud_mvp_rx.presenter.list.UserPresenter
import com.allana.crud_mvp_rx.presenter.list.UserView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), UserView {

    private lateinit var presenter: UserPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, InputActivity::class.java)
            startActivity(intent)
        }

        presenter = UserPresenter(this)
        presenter.getDataUser()
    }

    override fun onSuccess(user: List<DataItem>?) {
        val dataAdapter = UserAdapter(user)
        rv_list_item.layoutManager = LinearLayoutManager(this)
        rv_list_item.adapter = dataAdapter

        dataAdapter.setOnItemClickCallback(object : UserAdapter.OnItemClickCallback{
            override fun onItemClicked(data: DataItem?) {
                val intent = Intent(this@MainActivity, InputActivity::class.java)
                intent.putExtra("data", data)
                startActivity(intent)
            }

            override fun onItemDeleted(data: DataItem?) {
                AlertDialog.Builder(this@MainActivity).apply {
                    setMessage("Apakah yakin ingin menghapus data ${data?.mahasiswaNama}?")
                    setPositiveButton("HAPUS"){ dialogInterface: DialogInterface, i: Int ->
                        presenter.getDeleteUser(data?.idMahasiswa)
                        dialogInterface.dismiss()
                    }
                    setNegativeButton("BATAL"){ dialogInterface: DialogInterface, i: Int ->
                        dialogInterface.dismiss()
                    }
                }.show()
            }

        })
    }

    override fun onFailed() {
        Toast.makeText(applicationContext, "Gagal mendapatkan data", Toast.LENGTH_SHORT).show()
    }

    override fun onSuccessDeleted() {
        Toast.makeText(applicationContext, "Data berhasil dihapus", Toast.LENGTH_SHORT).show()
    }

    override fun onFailedDeleted() {
        Toast.makeText(applicationContext, "Data gagal dihapus", Toast.LENGTH_SHORT).show()
    }


}