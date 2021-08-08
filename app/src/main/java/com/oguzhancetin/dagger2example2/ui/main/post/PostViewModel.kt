package com.oguzhancetin.dagger2example2.ui.main.post

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.oguzhancetin.dagger2example2.SessionManager
import com.oguzhancetin.dagger2example2.model.Post
import com.oguzhancetin.dagger2example2.model.User
import com.oguzhancetin.dagger2example2.network.main.MainApi
import com.oguzhancetin.dagger2example2.ui.Resource
import com.oguzhancetin.dagger2example2.ui.auth.AuthResource
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PostViewModel @Inject constructor(
    private val sessionManager: SessionManager,
    private val mainApi: MainApi
) : ViewModel() {
    private val TAG = "PostViewModel"



    init {
        Log.d(TAG, "postviewmodel: ")
    }

    fun observePosts(): LiveData<Resource<List<Post>>> {
      var posts: MediatorLiveData<Resource<List<Post>>>? = null
        if(posts == null){
            posts = MediatorLiveData()
        }
        posts!!.value = Resource.Loading<List<Post>>(null)

        val source: LiveData<Resource<List<Post>>> = LiveDataReactiveStreams.fromPublisher(
            mainApi.getPOstsFromUser(sessionManager.getAutUser().value?.data?.id!!)
                ?.onErrorReturn { listOf<Post>(Post(-1,-1,"",""))}
                ?.map { selectPost(it) }
            !!.subscribeOn(Schedulers.io())
        )
        posts!!.addSource(source) {
            posts!!.postValue(it)
            posts!!.removeSource(source)
        }


        return posts!!

    }

    private fun selectPost(post: List<Post>): Resource<List<Post>> {
        post.forEach {
            if (it.id == -1) {
                return Resource.Error<List<Post>>("error", null)
            }
        }

        return Resource.Succes<List<Post>>(post)
    }


}







