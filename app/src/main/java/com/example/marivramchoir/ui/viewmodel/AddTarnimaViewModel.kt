package com.example.marivramchoir.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marivramchoir.data.model.Hymn
import com.example.marivramchoir.data.repository.AddTarnimaRepository
import com.example.marivramchoir.utlis.ApiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddTarnimaViewModel
@Inject constructor(private val addTarnimaRepository: AddTarnimaRepository) : ViewModel(){

    private val responseStateFlow : MutableStateFlow<ApiState>
            = MutableStateFlow(ApiState.Empty)
    val stateFlowResponse: StateFlow<ApiState> = responseStateFlow

    fun addHymen(hymn: Hymn) = viewModelScope.launch {
        responseStateFlow.value = ApiState.Loading
        addTarnimaRepository.addTarnima(hymn)
            .catch { e ->
                responseStateFlow.value = ApiState.Failure(e)
            }
            .collect {
                responseStateFlow.value = ApiState.AddTarnimaSuccess
            }
    }

}