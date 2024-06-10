package com.castro.helena.myopenweatherapi.domain.di

import com.castro.helena.myopenweatherapi.domain.usecase.GetWeatherUseCase
import com.castro.helena.myopenweatherapi.domain.usecase.GetWeatherUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {

    @Binds
    fun bindGetWeatherUseCase(getWeatherUseCaseImpl: GetWeatherUseCaseImpl) : GetWeatherUseCase
}