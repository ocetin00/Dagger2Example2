package com.oguzhancetin.dagger2example2.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class User (
    @SerializedName("id")
    @Expose
    val id: Int,

    @SerializedName("userName")
    @Expose
    val userName: String,

    @SerializedName("email")
    @Expose
    val email: String,

    @SerializedName("website")
    @Expose
    val website: String

){

}
