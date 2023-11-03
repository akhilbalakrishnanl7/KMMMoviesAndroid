package com.litmus7.movies.di

import com.litmus7.movies.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel {
        HomeViewModel(get())
    }
}