package com.example.marivramchoir.data.repository

import com.example.marivramchoir.data.api.HymnApiImpl
import com.example.marivramchoir.data.model.Servant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class AddServantRepository @Inject constructor(private val hymnApiImpl: HymnApiImpl) {

    suspend fun addServant(servant: Servant) = flow {
        emit(hymnApiImpl.addServant(servant))
    }.flowOn(Dispatchers.IO)

}