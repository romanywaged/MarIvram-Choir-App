package com.example.marivramchoir.data.api

import com.example.marivramchoir.data.model.Hymen
import retrofit2.http.GET

interface MarIvramApi {

    @GET("getAllTranim")
    suspend fun getAllHymens() : List<Hymen>
}