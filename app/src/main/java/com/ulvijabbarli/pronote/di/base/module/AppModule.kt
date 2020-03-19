package com.ulvijabbarli.pronote.di.base.module

import android.app.Application
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class AppModule {

    @Singleton
    @Provides
    fun provideGlideInstance(
        application: Application?,
        requestOptions: RequestOptions?
    ): RequestManager? {
        return Glide.with(application!!)
            .setDefaultRequestOptions(requestOptions!!)
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
    }

    @Singleton
    @Provides
    fun providePreferences(){

    }

}