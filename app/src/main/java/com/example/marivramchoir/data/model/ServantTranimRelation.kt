package com.example.marivramchoir.data.model

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation


data class ServantTranimRelation(

    @Embedded val servant: Servant,

    @Relation(
        parentColumn = "servant_Id",
        entityColumn = "tranimId",
        associateBy = Junction(ServantTranimRef::class)
    )

    val tranim: List<Hymen>
)
