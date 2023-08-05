package com.jtmcompany.parkingapplication.hilt

import android.content.Context
import androidx.room.Room
import com.jtmcompany.data.api.ApiInterface
import com.jtmcompany.data.datasource.local.ParkLocalDataSource
import com.jtmcompany.data.datasource.local.ParkLocalDataSourceImpl
import com.jtmcompany.data.datasource.remote.ParkRemoteDataSource
import com.jtmcompany.data.datasource.remote.ParkRemoteDataSourceImpl
import com.jtmcompany.data.db.ParkDao
import com.jtmcompany.data.db.ParkDatabase
import com.jtmcompany.data.repository.ParkRepositoryImpl
import com.jtmcompany.repository.ParkRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context): ParkDatabase{
        return Room.databaseBuilder(
            context,
            ParkDatabase::class.java,
            "park.db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideMovieDao(parkDatabase: ParkDatabase): ParkDao{
        return parkDatabase.parkDao()
    }

    @Singleton
    @Provides
    fun provideLocalDataSource(parkDao: ParkDao): ParkLocalDataSource{
        return ParkLocalDataSourceImpl(parkDao)
    }

    @Singleton
    @Provides
    fun provideRemoteDataSource(
        apiInterface: ApiInterface,
        @ApplicationContext context: Context
    ): ParkRemoteDataSource {
        return ParkRemoteDataSourceImpl(apiInterface, context)
    }

    @Singleton
    @Provides
    fun provideMovieRepository(
        parkRemoteDataSource: ParkRemoteDataSource,
        parkLocalDataSource: ParkLocalDataSource
    ): ParkRepository {
        return ParkRepositoryImpl(parkRemoteDataSource, parkLocalDataSource)
    }

}