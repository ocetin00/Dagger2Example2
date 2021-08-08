package com.oguzhancetin.dagger2example2.ui.main.profile

import android.content.ContentValues
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.oguzhancetin.dagger2example2.R
import com.oguzhancetin.dagger2example2.di.ViewModelFactoryModule
import com.oguzhancetin.dagger2example2.model.User
import com.oguzhancetin.dagger2example2.ui.auth.AuthResource
import com.oguzhancetin.dagger2example2.viewmodel.ViewModelProviderFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class ProfileFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProviderFactory

    private lateinit var viewmodel: ProfileViewModel

    private lateinit var email: TextView
    private lateinit var username: TextView
    private lateinit var website: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewmodel =
            ViewModelProvider(requireActivity(), viewModelFactory).get(ProfileViewModel::class.java)
        email = view.findViewById(R.id.email)
        website = view.findViewById(R.id.website)
        username = view.findViewById(R.id.username)

        subscribeObservers()

    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Toast.makeText(requireContext(), "Profile fragment", Toast.LENGTH_SHORT).show()

        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    private fun subscribeObservers() {
        viewmodel.getAuthenticatedUser().removeObservers(viewLifecycleOwner)

        viewmodel.getAuthenticatedUser().observe(viewLifecycleOwner, {
            when (it) {
                is AuthResource.AUTHENTICATED -> {
                    setUserDetail(it.data!!)
                }
                is AuthResource.Error -> {
                    Toast.makeText(requireContext(), "error : ${it.message}", Toast.LENGTH_SHORT).show()
                }
            }


        })
    }

    private fun setUserDetail(user: User) {
        username.text = user.userName
        email.text = user.email
        website.text = user.website
    }

}



