package com.example.movietmdbchallenge.di

import com.example.movietmdbchallenge.data.local.UserRepository
import com.example.movietmdbchallenge.remote.MovieRepository
import com.example.movietmdbchallenge.ui.home.HomeViewModel
import com.example.movietmdbchallenge.ui.login.LoginViewModel
import com.example.movietmdbchallenge.ui.profileUser.ProfileViewModel
import com.example.movietmdbchallenge.ui.register.RegisterViewModel
import com.example.movietmdbchallenge.ui.splashScreen.SplashViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModeModule = module {
    viewModel { HomeViewModel(get(),get()) }
    viewModel{ SplashViewModel(get())}
    viewModel{ LoginViewModel(get())}
    viewModel{ RegisterViewModel(get())}
    viewModel{ ProfileViewModel(get())}


}
val repositoryModule = module {
    single{
        UserRepository(get(),androidContext())
    }
    single {
        MovieRepository(get())
    }

}