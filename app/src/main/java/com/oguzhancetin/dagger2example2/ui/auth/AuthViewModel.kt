package com.oguzhancetin.dagger2example2.ui.auth

import android.util.Log
import androidx.lifecycle.*
import com.oguzhancetin.dagger2example2.SessionManager
import com.oguzhancetin.dagger2example2.model.User
import com.oguzhancetin.dagger2example2.network.auth.AuthAPi
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AuthViewModel @Inject constructor(
    private val authAPi: AuthAPi?,
    private val sessionManager: SessionManager
) : ViewModel() {
    val TAG = "viewmodel my"


    fun authenticateWithUd(id: Int) {
        sessionManager.authenticateWithId(queryUserId(id))
    }

    init {

        println("auth api")
    }

    fun observeAuthState(): MediatorLiveData<AuthResource<User>> {
        return sessionManager.getAutUser()
    }

    private fun queryUserId(id: Int): LiveData<AuthResource<User>> {
        return LiveDataReactiveStreams.fromPublisher(
            authAPi?.getUser(id)
                ?.onErrorReturn { User(-1, "", "", "") }
                ?.map { selectUser(it) }
            !!.subscribeOn(Schedulers.io())
        )
    }

    private fun selectUser(user:User): AuthResource<User> {
        if(user.id == -1){
            return AuthResource.NOT_AUTHENTICATED(null)
        }
        return AuthResource.AUTHENTICATED(user)
    }

}