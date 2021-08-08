package com.oguzhancetin.dagger2example2.network.auth

import com.oguzhancetin.dagger2example2.model.User
import io.reactivex.Flowable
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.concurrent.Flow


interface AuthAPi {

    @GET("users/{id}")
    fun getUser(@Path("id") id: Int): Flowable<User>
}