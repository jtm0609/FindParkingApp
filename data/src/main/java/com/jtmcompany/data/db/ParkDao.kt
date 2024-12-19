package com.jtmcompany.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jtmcompany.data.data.entity.response.local.ParkEntity
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Data Access Object
 *
 * 필요한 데이터를 DB 에서 가지고 오기 위한 쿼리를 작성.
 */
@Dao
interface ParkDao {

    @Query("SELECT * FROM park")
    fun getAllParks(): Single<List<ParkEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insetParks(movies: List<ParkEntity>): Completable

    @Query("DELETE FROM park")
    fun deleteParks() : Completable
}