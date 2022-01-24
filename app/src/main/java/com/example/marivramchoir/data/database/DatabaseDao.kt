package com.example.marivramchoir.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.marivramchoir.data.model.Hymen


@Dao
interface DatabaseDao {

    //------------------------------ Dashboard Activity-----------------------------
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHymen(hymens: List<Hymen>)

    @Query("Delete from hymen_table")
    suspend fun deleteAllHymens()

    //------------------------------ Dashboard Activity-----------------------------
    @Query("SELECT * FROM hymen_table")
    suspend fun getAllHymensDb() : List<Hymen>

}