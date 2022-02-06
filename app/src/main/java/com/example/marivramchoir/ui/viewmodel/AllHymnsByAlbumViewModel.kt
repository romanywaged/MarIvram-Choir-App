package com.example.marivramchoir.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marivramchoir.data.repository.AllHymensByAlbumRepository
import com.example.marivramchoir.utlis.ApiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.hypot

@HiltViewModel
class AllHymnsByAlbumViewModel
@Inject constructor(private val allHymensByAlbumRepository: AllHymensByAlbumRepository) :ViewModel(){

    private val dataStateFlow : MutableStateFlow<ApiState>
            = MutableStateFlow(ApiState.Empty)
    val stateFlowData: StateFlow<ApiState> = dataStateFlow

    fun getAllHymensByAlbum(type:String) = viewModelScope.launch {
        dataStateFlow.value = ApiState.Loading
        allHymensByAlbumRepository.getAllHymensByAlbum(type)
            .catch { e ->
                dataStateFlow.value = ApiState.Failure(e)
            }
            .collect { hymens ->
                dataStateFlow.value = ApiState.GetAllHymensSuccess(hymens)
            }
    }

}