package com.example.marivramchoir.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "hymen_table")
data class Hymen(

    @PrimaryKey
    @SerializedName("tranimId")
    @ColumnInfo(name = "tranimId")
    var id : Int? = null,

    @SerializedName("tranimName")
    @ColumnInfo(name = "tranimName")
    var hymenName : String? = null,

    @SerializedName("tranimWords")
    @ColumnInfo(name = "tranimWords")
    var hymenWords : String? = null,

    @SerializedName("tranimUrl")
    @ColumnInfo(name = "tranimUrl")
    var hymenUrl : String? = null,


    @SerializedName("tarnimaType")
    @ColumnInfo(name = "tarnimaType")
    var hymenType : String? = null

)
