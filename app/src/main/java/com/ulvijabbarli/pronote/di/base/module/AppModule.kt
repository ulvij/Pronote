package com.ulvijabbarli.pronote.di.base.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.ulvijabbarli.pronote.data.DataHelper
import com.ulvijabbarli.pronote.data.DataManager
import com.ulvijabbarli.pronote.data.local.db.DbHelper
import com.ulvijabbarli.pronote.data.local.db.DbManager
import com.ulvijabbarli.pronote.data.local.prefs.PreferencesHelper
import com.ulvijabbarli.pronote.data.local.prefs.PreferencesManager
import com.ulvijabbarli.pronote.util.Constants
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class AppModule {

    @Provides
    @Singleton
    fun provideDataHelper(dataManager: DataManager): DataHelper {
        return dataManager
    }

    @Provides
    @Singleton
    fun provideDbHelper(dbManager: DbManager): DbHelper {
        return dbManager
    }

    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application
    }


    @Provides
    @Singleton
    fun providePreferencesHelper(preferencesManager: PreferencesManager): PreferencesHelper {
        return preferencesManager
    }

    @Provides
    @Singleton
    fun provideSharedPreferences(context: Context, prefName: String): SharedPreferences {
        return context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun providePreferenceName(): String {
        return PreferencesManager.PREF_NAME
    }


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


}