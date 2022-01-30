package com.example.marivramchoir.data.repository

import com.example.marivramchoir.data.api.HymenApiImpl
import com.example.marivramchoir.data.model.Hymen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class AddTarnimaRepository @Inject constructor(private val hymenApiImpl: HymenApiImpl) {


    suspend fun addTarnima(hymen: Hymen) = flow {
        emit(hymenApiImpl.addTarnima(hymen))
    }.flowOn(Dispatchers.IO)

}