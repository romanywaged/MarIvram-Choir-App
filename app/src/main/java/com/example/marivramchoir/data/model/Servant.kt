package com.example.marivramchoir.data.model

import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "servant_table")
data class Servant(

    @PrimaryKey
    @SerializedName("servantId")
    @ColumnInfo(name = "servant_Id")
    var servantId: Int? = null,

    @SerializedName("servantName")
    @ColumnInfo(name = "servant_name")
    var servantName: String? = null,

    @SerializedName("servantLocation")
    @ColumnInfo(name = "servant_location")
    var servantLocation: String? = null,

    @SerializedName("servantDate")
    @ColumnInfo(name = "servant_date")
    var servantDate:String? = null
)
