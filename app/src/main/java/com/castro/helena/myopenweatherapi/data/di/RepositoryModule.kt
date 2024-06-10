package com.castro.helena.myopenweatherapi.data.di

import com.castro.helena.myopenweatherapi.data.repository.WeatherRepository
import com.castro.helena.myopenweatherapi.data.repository.WeatherRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindWeatherRepository(weatherRepositoryImpl: WeatherRepositoryImpl) : WeatherRepository

}