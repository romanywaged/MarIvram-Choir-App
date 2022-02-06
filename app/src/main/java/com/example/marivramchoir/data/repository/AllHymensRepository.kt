package com.example.marivramchoir.data.repository

import com.example.marivramchoir.data.api.HymnApiImpl
import com.example.marivramchoir.data.database.DatabaseHelper
import com.example.marivramchoir.data.model.Hymn
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class AllHymensRepository @Inject constructor(private val hymnApiImpl: HymnApiImpl, private val databaseHelper: DatabaseHelper) {
    suspend fun getAllHymensDb() : Flow<List<Hymn>> = flow {
        emit(databaseHelper.getAllHymens())
    }.flowOn(Dispatchers.IO)

    suspend fun getAllHymens(): Flow<List<Hymn>> = flow {
        emit(hymnApiImpl.getAllHymens())
    }.flowOn(Dispatchers.IO)

    suspend fun insertHymensDb(hymns:List<Hymn>) {
        databaseHelper.insertAllHymens(hymns)
    }

    suspend fun deleteAllHymens() {
        databaseHelper.deleteAllHymens()
    }
}