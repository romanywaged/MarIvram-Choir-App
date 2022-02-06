package com.example.marivramchoir.data.database

import com.example.marivramchoir.data.model.Hymn
import javax.inject.Inject

class DatabaseHelper @Inject constructor(private val databaseDao: DatabaseDao) {

    suspend fun insertAllHymens(hymns:List<Hymn>) = databaseDao.insertHymen(hymns)

    suspend fun deleteAllHymens() = databaseDao.deleteAllHymens()

    suspend fun getAllHymens() :List<Hymn> = databaseDao.getAllHymensDb()

    suspend fun getHymensByAlbum(type:String): List<Hymn> = databaseDao.getHymensByAlbum(type)

}