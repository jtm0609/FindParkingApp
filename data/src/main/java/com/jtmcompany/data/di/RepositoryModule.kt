package com.jtmcompany.data.di

import com.jtmcompany.data.datasource.local.ParkLocalDataSource
import com.jtmcompany.data.datasource.local.PrefDataSource
import com.jtmcompany.data.datasource.remote.ParkRemoteDataSource
import com.jtmcompany.data.repository.ParkRepositoryImpl
import com.jtmcompany.repository.ParkRepository
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
    fun provideMovieRepository(
        parkRemoteDataSource: ParkRemoteDataSource,
        parkLocalDataSource: ParkLocalDataSource,
        prefDataSource: PrefDataSource
    ): ParkRepository {
        return ParkRepositoryImpl(parkRemoteDataSource, parkLocalDataSource, prefDataSource)
    }
}