package com.example.marivramchoir.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marivramchoir.data.model.Hymen
import com.example.marivramchoir.data.repository.DashboardRepository
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
class DashboardViewModel
@Inject constructor(private val dashboardRepository: DashboardRepository): ViewModel() {

    private val responseStateFlow : MutableStateFlow<ApiState>
    = MutableStateFlow(ApiState.Empty)
    val stateFlowResponse:StateFlow<ApiState> = responseStateFlow

    fun getAllHymens() = viewModelScope.launch {
        responseStateFlow.value = ApiState.Loading
        dashboardRepository.getAllHymens()
            .catch { e ->
                responseStateFlow.value = ApiState.Failure(e)
            }
            .collect { hymens ->
                responseStateFlow.value = ApiState.GetAllHymensSuccess(hymens)
            }
    }

    fun insertAllHymens(hymens:List<Hymen>) = viewModelScope.launch(Dispatchers.IO) {
        dashboardRepository.insertHymensDb(hymens)
    }

    fun deleteAllHymens() = viewModelScope.launch(Dispatchers.IO) {
        dashboardRepository.deleteAllHymens()
    }

}