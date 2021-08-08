package com.oguzhancetin.dagger2example2

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Toast
import com.oguzhancetin.dagger2example2.ui.auth.AuthActivity
import com.oguzhancetin.dagger2example2.ui.auth.AuthResource
import com.oguzhancetin.dagger2example2.ui.main.MainActivity
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject



abstract class BaseActivty : DaggerAppCompatActivity() {
    private  val TAG = "BaseActivty"
    @Inject
    lateinit var sessionManager: SessionManager



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        subscribeObservers()
    }

    private fun subscribeObservers() {
        sessionManager.getAutUser().observe(this, {
            Log.d(TAG, "subscribeObservers from base activty ")
            it?.let { resource ->
                when (resource) {
                    is AuthResource.AUTHENTICATED -> {
                        Log.d(TAG, "subscribeObservers: AUTHENTICATED")

                    }
                    is AuthResource.NOT_AUTHENTICATED -> {
                        Log.d(TAG, "subscribeObservers: NOT_AUTHENTICATED")
                        navLoginScrean()
                    }
                    is AuthResource.Loading -> {

                    }
                    is AuthResource.Error -> {
                        Log.d(TAG, "subscribeObservers: ${resource.message}")
                    }

                }
            }
        })
    }

    private fun navLoginScrean() {
       val intent =  Intent(this, AuthActivity::class.java)
        startActivity(intent)
        finish()
    }


}