package com.example.marivramchoir.utlis

import com.example.marivramchoir.data.model.Hymen

sealed class ApiState {
    object Loading : ApiState()

    class Failure(val msg:Throwable) : ApiState()

    object Empty : ApiState()

    class GetAllHymensSuccess(val hymens:List<Hymen>) : ApiState()

    object AddTarnimaSuccess : ApiState()

    object AddServantSuccess : ApiState()
}