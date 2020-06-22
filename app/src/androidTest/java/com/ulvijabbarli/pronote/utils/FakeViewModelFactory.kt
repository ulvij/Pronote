package com.ulvijabbarli.pronote.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

internal class FakeViewModelFactory(private val viewModel: ViewModel) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return viewModel as T
    }
}