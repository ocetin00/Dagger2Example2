package com.oguzhancetin.dagger2example2.ui

import com.oguzhancetin.dagger2example2.ui.auth.AuthResource

sealed class Resource<T> (
    val data: T? = null,
    val message: String? = null
    )
 {
    class Succes<T>(data: T) : Resource<T>(data)
    class Loading<T>(data: T? = null) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
}