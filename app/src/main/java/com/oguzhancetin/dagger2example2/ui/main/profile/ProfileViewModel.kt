package com.oguzhancetin.dagger2example2.ui.main.profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.oguzhancetin.dagger2example2.SessionManager
import com.oguzhancetin.dagger2example2.model.User
import com.oguzhancetin.dagger2example2.ui.auth.AuthResource
import javax.inject.Inject

class ProfileViewModel @Inject constructor(private val sessionManager: SessionManager ): ViewModel() {
    private  val TAG = "ProfileViewModel"
    
    init {
        Log.d(TAG, "viewmodel provide: ")
    }
    fun getAuthenticatedUser(): LiveData<AuthResource<User>> {
        return sessionManager.getAutUser()
    }

}