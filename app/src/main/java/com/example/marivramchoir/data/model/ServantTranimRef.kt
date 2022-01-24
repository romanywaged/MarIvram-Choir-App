package com.example.marivramchoir.data.model

import androidx.annotation.NonNull
import androidx.room.Entity


@Entity(primaryKeys = ["servant_Id", "tranimId"])
data class ServantTranimRef(

    val servant_Id: Int,

    val tranimId: Int
)
