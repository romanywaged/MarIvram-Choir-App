package com.example.marivramchoir.data.api

import com.example.marivramchoir.data.model.Hymen
import com.example.marivramchoir.data.model.Servant
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HymenApiImpl @Inject constructor(private val marIvramApi: MarIvramApi) {

    suspend fun getAllHymens() : List<Hymen> = marIvramApi.getAllHymens()

    suspend fun addTarnima(hymen: Hymen) = marIvramApi.addTarnima(hymen)

    suspend fun addServant(servant: Servant) = marIvramApi.addServant(servant)
}