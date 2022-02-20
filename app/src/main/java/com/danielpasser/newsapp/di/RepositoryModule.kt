package com.danielpasser.newsapp.di

import com.danielpasser.newsapp.api.Api
import com.danielpasser.newsapp.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideRepository(retrofit: Api) : Repository =Repository(retrofit)
}