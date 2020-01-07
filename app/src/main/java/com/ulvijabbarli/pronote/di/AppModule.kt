package com.ulvijabbarli.pronote.di

import android.app.Application
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
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

}