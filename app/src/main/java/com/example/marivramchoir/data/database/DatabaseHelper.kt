package com.example.marivramchoir.data.database

import com.example.marivramchoir.data.model.Hymen
import javax.inject.Inject

class DatabaseHelper @Inject constructor(private val databaseDao: DatabaseDao) {

    suspend fun insertAllHymens(hymens:List<Hymen>) = databaseDao.insertHymen(hymens)

    suspend fun deleteAllHymens() = databaseDao.deleteAllHymens()

    suspend fun getAllHymens() :List<Hymen> = databaseDao.getAllHymensDb()

}