package com.example.marivramchoir.data.repository

import com.example.marivramchoir.data.api.HymenApiImpl
import com.example.marivramchoir.data.model.Hymen
import com.example.marivramchoir.data.model.Servant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class AddServantRepository @Inject constructor(private val hymenApiImpl: HymenApiImpl) {

    suspend fun addServant(servant: Servant) = flow {
        emit(hymenApiImpl.addServant(servant))
    }.flowOn(Dispatchers.IO)

}