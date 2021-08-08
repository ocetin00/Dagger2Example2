package com.oguzhancetin.dagger2example2.ui.auth

// A generic class that contains data and status about loading this data.
sealed class AuthResource<T>(
    val data: T? = null,
    val message: String? = null
) {
    class AUTHENTICATED<T>(data: T) : AuthResource<T>(data)
    class NOT_AUTHENTICATED<T>(data: T? = null) : AuthResource<T>(data)
    class Loading<T>(data: T? = null) : AuthResource<T>(data)
    class Error<T>(message: String, data: T? = null) : AuthResource<T>(data, message)
}