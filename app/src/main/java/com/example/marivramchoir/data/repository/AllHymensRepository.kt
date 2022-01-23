package com.example.marivramchoir.data.repository

import com.example.marivramchoir.data.api.HymenApiImpl
import com.example.marivramchoir.data.model.Hymen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class AllHymensRepository @Inject constructor(private val hymenApiImpl: HymenApiImpl) {

    suspend fun getAllHymens(): Flow<List<Hymen>> = flow {
        emit(hymenApiImpl.getAllHymens())
    }.flowOn(Dispatchers.IO)

}