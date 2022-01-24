package com.example.marivramchoir.data.model

import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "servant_table")
data class Servant(


    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "servant_Id")
    var servantId: Int,

    @ColumnInfo(name = "servant_name")
    val servantName: String? = null,

    @ColumnInfo(name = "servant_location")
    val servantLocation: String? = null,

    @ColumnInfo(name = "servant_date")
    val servantDate:String? = null
)
