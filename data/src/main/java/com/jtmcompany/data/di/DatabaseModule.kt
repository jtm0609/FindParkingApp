package com.jtmcompany.data.di

import android.content.Context
import androidx.room.Room
import com.jtmcompany.data.db.ParkDao
import com.jtmcompany.data.db.ParkDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideRoomDatabase(@ApplicationContext context: Context): ParkDatabase {
        return Room.databaseBuilder(
            context,
            ParkDatabase::class.java,
            "park.db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideMovieDao(parkDatabase: ParkDatabase): ParkDao {
        return parkDatabase.parkDao()
    }
}