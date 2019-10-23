package com.android.koinapp.web


import com.android.koinapp.model.UserModel
import io.reactivex.Flowable
import retrofit2.http.GET


interface RestClient {

    @GET("/users")
    fun getUsers(): Flowable<List<UserModel>>

}
