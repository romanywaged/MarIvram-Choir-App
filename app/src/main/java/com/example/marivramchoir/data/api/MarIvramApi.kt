package com.example.marivramchoir.data.api

import com.example.marivramchoir.data.model.Hymn
import com.example.marivramchoir.data.model.Servant
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface MarIvramApi {

    @GET("getAllTranim")
    suspend fun getAllHymens() : List<Hymn>

    @POST("AddTarnima")
    suspend fun addTarnima(@Body hymn: Hymn)

    @POST("AddServant")
    suspend fun addServant(@Body servant: Servant)

}