package com.castro.helena.myopenweatherapi.data.di

import com.castro.helena.myopenweatherapi.data.remote.datasource.RemoteDataSourceImpl
import com.castro.helena.myopenweatherapi.data.remote.datasource.RemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {

    @Binds
    @Singleton
    fun bindRemoteDataSource(remoteDataSourceImpl: RemoteDataSourceImpl): RemoteDataSource

}