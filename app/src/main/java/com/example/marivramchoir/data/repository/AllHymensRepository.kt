package com.example.marivramchoir.data.repository

import com.example.marivramchoir.data.database.DatabaseHelper
import com.example.marivramchoir.data.model.Hymen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class AllHymensRepository @Inject constructor(private val databaseHelper: DatabaseHelper) {
    suspend fun getAllHymensDb() : Flow<List<Hymen>> = flow {
        emit(databaseHelper.getAllHymens())
    }.flowOn(Dispatchers.IO)
}