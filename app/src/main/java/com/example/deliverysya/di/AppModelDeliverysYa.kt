package com.example.deliverysya.di

import android.content.Context
import com.example.deliverysya.repository.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModelDeliverysYa {
    @Provides
    @Singleton
    fun provideContext(@ApplicationContext appContext: Context):Context{
        return appContext
    }

    @Provides
    fun provideAuthRepository() = AuthRepository()
}

