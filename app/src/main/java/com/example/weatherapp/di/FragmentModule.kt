package com.example.weatherapp.di

import com.example.weatherapp.navigator.Navigator
import com.example.weatherapp.navigator.NavigatorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@InstallIn(FragmentComponent::class)
@Module
abstract class FragmentModule {

    @Binds
    abstract fun provideNavigator(navigatorImpl: NavigatorImpl): Navigator
}