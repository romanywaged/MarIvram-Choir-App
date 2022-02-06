package com.example.marivramchoir.utlis

import com.example.marivramchoir.data.model.Hymn

sealed class ApiState {
    object Loading : ApiState()

    class Failure(val msg:Throwable) : ApiState()

    object Empty : ApiState()

    class GetAllHymensSuccess(val hymns:List<Hymn>) : ApiState()

    object AddTarnimaSuccess : ApiState()

    object AddServantSuccess : ApiState()
}