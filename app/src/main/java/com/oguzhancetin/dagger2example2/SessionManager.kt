package com.oguzhancetin.dagger2example2

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.oguzhancetin.dagger2example2.model.User
import com.oguzhancetin.dagger2example2.ui.auth.AuthResource
import javax.inject.Inject
import javax.inject.Singleton

private const val TAG = "SessionManager"

@Singleton
class SessionManager @Inject constructor() {
    
    

    private val cachedUser: MediatorLiveData<AuthResource<User>> = MediatorLiveData()

    fun authenticateWithId(source: LiveData<AuthResource<User>>){
        cachedUser.let {
            it.value =  AuthResource.Loading<User>()
            it.addSource(source,{
                cachedUser.value = it
                cachedUser.removeSource(source)
            })

            Log.d(TAG, "authenticateWithId: ${it.value?.data?.id}")
        }
    }

    fun logOut(){
        Log.d(TAG, "logOut: ")
        cachedUser.value = AuthResource.NOT_AUTHENTICATED<User>()
    }

    fun getAutUser(): MediatorLiveData<AuthResource<User>> {
        return cachedUser
    }
}