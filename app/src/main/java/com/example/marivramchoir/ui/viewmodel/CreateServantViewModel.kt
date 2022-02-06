package com.example.marivramchoir.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marivramchoir.data.model.Servant
import com.example.marivramchoir.data.repository.AddServantRepository
import com.example.marivramchoir.utlis.ApiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateServantViewModel
@Inject constructor(private val addServantRepository: AddServantRepository) :ViewModel() {

    private val responseStateFlow : MutableStateFlow<ApiState>
            = MutableStateFlow(ApiState.Empty)
    val stateFlowResponse: StateFlow<ApiState> = responseStateFlow

    fun createServant(servant: Servant) = viewModelScope.launch {
        responseStateFlow.value = ApiState.Loading
        addServantRepository.addServant(servant)
            .catch { e ->
                responseStateFlow.value = ApiState.Failure(e)
            }
            .collect {
                responseStateFlow.value = ApiState.AddServantSuccess
            }
    }
}