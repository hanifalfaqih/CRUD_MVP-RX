package com.allana.crud_mvp_rx.network

import com.allana.crud_mvp_rx.model.action.ResponseAction
import com.allana.crud_mvp_rx.model.data.ResponseGetData
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import retrofit2.Call
import retrofit2.http.*

interface UserService {

    // getData
    @GET("getData.php")
    fun getData(): Flowable<ResponseGetData>

    // insertData
    @FormUrlEncoded
    @POST("insertData.php")
    fun insertData(
        @Field("mahasiswa_nama") mahasiswa_nama: String,
        @Field("mahasiswa_nohp") mahasiswa_nohp: String,
        @Field("mahasiswa_alamat") mahasiswa_alamat: String
    ): Flowable<ResponseAction>

    // updateData
    @FormUrlEncoded
    @POST("updateData.php")
    fun updateData(
        @Field("id_mahasiswa") id_mahasiswa: String,
        @Field("mahasiswa_nama") mahasiswa_nama: String,
        @Field("mahasiswa_nohp") mahasiswa_nohp: String,
        @Field("mahasiswa_alamat") mahasiswa_alamat: String
    ): Flowable<ResponseAction>

    // deleteData
    @FormUrlEncoded
    @POST("deleteData.php")
    fun deleteData(
        @Field("id_mahasiswa") id_mahasiswa: String
    ): Flowable<ResponseAction>


}