package com.oguzhancetin.dagger2example2.di

import android.annotation.SuppressLint
import android.app.Application
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.oguzhancetin.dagger2example2.BaseApplication
import com.oguzhancetin.dagger2example2.R
import com.oguzhancetin.dagger2example2.util.Constants
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule {
    companion object {
        @SuppressLint("ResourceType")
        @Provides
        @Singleton
        fun provideRequestOptions() :RequestOptions{
            return RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)

        }

        @Provides
        @Singleton
        fun provideGlideInstance(application: Application,requestOptions: RequestOptions): RequestManager {
            return Glide.with(application)
                .setDefaultRequestOptions(requestOptions)
        }
        @Provides
        @Singleton
        fun provideDrawble(application: Application): Drawable {
            return ContextCompat.getDrawable(application,R.drawable.ic_launcher_background)!!
        }

        @Provides
        @Singleton
        fun provideRetrofit(application: Application): Retrofit {
            return Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        }
    }

}