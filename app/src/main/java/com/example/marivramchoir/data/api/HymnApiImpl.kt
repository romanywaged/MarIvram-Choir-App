package com.example.marivramchoir.data.api

import com.example.marivramchoir.data.model.Hymn
import com.example.marivramchoir.data.model.Servant
import javax.inject.Inject

class HymnApiImpl @Inject constructor(private val marIvramApi: MarIvramApi) {

    suspend fun getAllHymens() : List<Hymn> = marIvramApi.getAllHymens()

    suspend fun addTarnima(hymn: Hymn) = marIvramApi.addTarnima(hymn)

    suspend fun addServant(servant: Servant) = marIvramApi.addServant(servant)
}