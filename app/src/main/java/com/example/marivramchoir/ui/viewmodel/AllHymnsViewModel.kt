package com.example.marivramchoir.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marivramchoir.data.model.Hymn
import com.example.marivramchoir.data.repository.AllHymensRepository
import com.example.marivramchoir.utlis.ApiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllHymnsViewModel
@Inject constructor(private val allHymensRepository: AllHymensRepository) : ViewModel() {

    private val dataStateFlow : MutableStateFlow<ApiState>
            = MutableStateFlow(ApiState.Empty)
    val stateFlowData: StateFlow<ApiState> = dataStateFlow

    fun getAllHymensFromDb() = viewModelScope.launch {
        dataStateFlow.value = ApiState.Loading
        allHymensRepository.getAllHymensDb()
            .catch { e ->
                dataStateFlow.value = ApiState.Failure(e)
            }
            .collect { hymens ->
                dataStateFlow.value = ApiState.GetAllHymensSuccess(hymens)
            }
    }
    private val responseStateFlow : MutableStateFlow<ApiState>
            = MutableStateFlow(ApiState.Empty)
    val stateFlowResponse:StateFlow<ApiState> = responseStateFlow

    fun getAllHymens() = viewModelScope.launch {
        responseStateFlow.value = ApiState.Loading
        allHymensRepository.getAllHymens()
            .catch { e ->
                responseStateFlow.value = ApiState.Failure(e)
            }
            .collect { hymens ->
                responseStateFlow.value = ApiState.GetAllHymensSuccess(hymens)
            }
    }

    fun insertAllHymens(hymns:List<Hymn>) = viewModelScope.launch(Dispatchers.IO) {
        allHymensRepository.insertHymensDb(hymns)
    }

    fun deleteAllHymens() = viewModelScope.launch(Dispatchers.IO) {
        allHymensRepository.deleteAllHymens()
    }

}