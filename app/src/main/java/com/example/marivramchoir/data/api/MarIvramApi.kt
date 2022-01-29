package com.example.marivramchoir.data.api

import com.example.marivramchoir.data.model.Hymen
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface MarIvramApi {

    @GET("getAllTranim")
    suspend fun getAllHymens() : List<Hymen>

    @POST("AddTarnima")
    suspend fun addTarnima(@Body hymen: Hymen)

}