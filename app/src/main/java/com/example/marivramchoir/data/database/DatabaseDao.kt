package com.example.marivramchoir.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.marivramchoir.data.model.Hymn


@Dao
interface DatabaseDao {

    //------------------------------ Dashboard Activity-----------------------------
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHymen(hymns: List<Hymn>)

    @Query("Delete from hymen_table")
    suspend fun deleteAllHymens()

    //------------------------------ Dashboard Activity-----------------------------
    @Query("SELECT * FROM hymen_table")
    suspend fun getAllHymensDb() : List<Hymn>

    //------------------------------ Dashboard Activity-----------------------------
    @Query("SELECT * FROM hymen_table where tarnimaType = :type")
    suspend fun getHymensByAlbum(type:String) : List<Hymn>

}