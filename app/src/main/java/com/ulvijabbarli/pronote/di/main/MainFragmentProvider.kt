package com.ulvijabbarli.pronote.di.main

import com.ulvijabbarli.pronote.ui.main.notes.NotesFragment
import com.ulvijabbarli.pronote.ui.main.notes.NotesFragmentModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentProvider {

    @ContributesAndroidInjector(modules = [NotesFragmentModule::class])
    abstract fun provideNotesFragmentFactory(): NotesFragment


}