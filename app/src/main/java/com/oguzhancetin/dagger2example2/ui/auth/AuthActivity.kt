package com.oguzhancetin.dagger2example2.ui.auth

import android.content.ContentValues.TAG
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.RequestManager
import com.oguzhancetin.dagger2example2.BaseActivty
import com.oguzhancetin.dagger2example2.R
import com.oguzhancetin.dagger2example2.model.User
import com.oguzhancetin.dagger2example2.ui.main.MainActivity
import com.oguzhancetin.dagger2example2.viewmodel.ViewModelProviderFactory
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class AuthActivity: DaggerAppCompatActivity() {

    private lateinit var progressBar: ProgressBar
    private lateinit var userId: EditText
    private lateinit var viewModel: AuthViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelProviderFactory

    @Inject
    lateinit var logo: Drawable

    @Inject
    lateinit var requestManager: RequestManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        progressBar = findViewById(R.id.progressBar)

        viewModel = ViewModelProvider(this, viewModelFactory).get(AuthViewModel::class.java)

        loadLogo()

        userId = findViewById(R.id.editTextTextUserId)
        (findViewById(R.id.buttonLogin) as Button).setOnClickListener {
            attemptLogin()
        }
        subscribeObservers()

    }

    private fun subscribeObservers() {
        viewModel.observeAuthState().observe(this, Observer<AuthResource<User>> {
            it?.let { resource ->
                when (resource) {
                    is AuthResource.AUTHENTICATED -> {
                        Log.d(TAG, "subscribeObservers: ${resource.data?.id}")
                        showProgressBar(false)
                        onLoginSuccess()
                    }
                    is AuthResource.NOT_AUTHENTICATED -> {
                        showProgressBar(false)
                    }
                    is AuthResource.Loading -> {
                        showProgressBar(true)
                    }
                    is AuthResource.Error -> {
                        showProgressBar(false)
                        Toast.makeText(
                            this,
                            "${resource.message} \n  you enter number betwen 1-10",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        })
    }

    private fun attemptLogin() {
        if (TextUtils.isEmpty((userId.text).toString())) {
            return
        }
        viewModel.authenticateWithUd((userId.text).toString().toInt())
    }
    private fun showProgressBar(isVisible: Boolean){
        if(isVisible){
            progressBar.visibility = View.VISIBLE
        }else{
            progressBar.visibility = View.INVISIBLE
        }
    }


    private fun loadLogo() {
        requestManager.load(logo)
            .into((findViewById(R.id.imageViewAuth)) as ImageView)
    }
    private fun onLoginSuccess() {
        Intent(this, MainActivity::class.java).also {
            startActivity(it)
            finish()
        }
    }
}