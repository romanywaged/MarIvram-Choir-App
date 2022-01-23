package com.example.marivramchoir.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(tableName = "hymen_table")
data class Hymen(

    @SerializedName("tranimId")
    @ColumnInfo(name = "tranimId")
    val id : Int? = null,

    @SerializedName("tranimName")
    @ColumnInfo(name = "tranimName")
    val hymenName : String? = null,

    @SerializedName("tranimWords")
    @ColumnInfo(name = "tranimWords")
    val hymenWords : String? = null,

    @SerializedName("tranimUrl")
    @ColumnInfo(name = "tranimUrl")
    val hymenUrl : String? = null

)
