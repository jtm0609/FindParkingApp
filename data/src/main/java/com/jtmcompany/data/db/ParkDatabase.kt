package com.jtmcompany.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jtmcompany.data.data.entity.response.local.ParkEntity

@Database(entities = [ParkEntity::class], version =2, exportSchema = false)
abstract class ParkDatabase: RoomDatabase() {
    abstract fun parkDao(): ParkDao
}