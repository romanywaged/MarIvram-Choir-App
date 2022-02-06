package com.example.marivramchoir.data.repository

import com.example.marivramchoir.data.database.DatabaseHelper
import com.example.marivramchoir.data.model.Hymn
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class AllHymensByAlbumRepository @Inject constructor(private var databaseHelper: DatabaseHelper) {

    suspend fun getAllHymensByAlbum(type:String) : Flow<List<Hymn>> = flow {
        emit(databaseHelper.getHymensByAlbum(type))
    }.flowOn(Dispatchers.IO)


}