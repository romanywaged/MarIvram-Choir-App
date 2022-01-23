package com.example.marivramchoir.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marivramchoir.data.repository.AllHymensRepository
import com.example.marivramchoir.utlis.ApiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllHymensViewModel
@Inject constructor(private val allHymensRepository: AllHymensRepository): ViewModel() {

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

}