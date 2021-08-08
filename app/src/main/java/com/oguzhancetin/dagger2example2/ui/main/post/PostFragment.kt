package com.oguzhancetin.dagger2example2.ui.main.post

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.oguzhancetin.dagger2example2.R
import com.oguzhancetin.dagger2example2.model.User
import com.oguzhancetin.dagger2example2.ui.Resource
import com.oguzhancetin.dagger2example2.ui.auth.AuthResource
import com.oguzhancetin.dagger2example2.util.VerticalSpaceItemDecoration
import com.oguzhancetin.dagger2example2.viewmodel.ViewModelProviderFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class PostFragment : DaggerFragment() {
    private val TAG = "PostFragment"
    private lateinit var rc: RecyclerView
    private lateinit var viewmodel: PostViewModel

    @Inject
    lateinit var viewmodelProvide: ViewModelProviderFactory

    @Inject
    lateinit var rcAdapter: PostRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_post, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rc = view.findViewById(R.id.recycler_view)

        viewmodel = ViewModelProvider(viewModelStore, viewmodelProvide).get(PostViewModel::class.java)

        initRecyclerView()
        subscribeObservers()
    }


    private fun subscribeObservers() {
        viewmodel.observePosts().removeObservers(viewLifecycleOwner)
        viewmodel.observePosts().observe(viewLifecycleOwner, {
            when (it) {
                is Resource.Succes -> it.data?.let { it1 -> rcAdapter.setPosts(it1) }
                is Resource.Error -> Log.d(TAG, " Error ")
                is Resource.Loading -> Log.d(TAG, "Loading ")
            }
        })
    }

    private fun initRecyclerView() {
        rc.apply {
            layoutManager = LinearLayoutManager(activity)
            addItemDecoration(VerticalSpaceItemDecoration(15))
            adapter = rcAdapter
        }
    }

}