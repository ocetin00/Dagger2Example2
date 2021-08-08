package com.oguzhancetin.dagger2example2.network.main

import com.oguzhancetin.dagger2example2.model.Post
import com.oguzhancetin.dagger2example2.model.User
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MainApi {

    @GET("posts")
    fun getPOstsFromUser(@Query("userId") userId: Int): Flowable<List<Post>>
}