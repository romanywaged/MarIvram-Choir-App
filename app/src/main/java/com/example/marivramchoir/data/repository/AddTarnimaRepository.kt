package com.example.marivramchoir.data.repository

import com.example.marivramchoir.data.api.HymnApiImpl
import com.example.marivramchoir.data.model.Hymn
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class AddTarnimaRepository @Inject constructor(private val hymnApiImpl: HymnApiImpl) {


    suspend fun addTarnima(hymn: Hymn) = flow {
        emit(hymnApiImpl.addTarnima(hymn))
    }.flowOn(Dispatchers.IO)

}