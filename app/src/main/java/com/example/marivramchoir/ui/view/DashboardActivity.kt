package com.example.marivramchoir.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.marivramchoir.R
import com.example.marivramchoir.ui.viewmodel.DashboardViewModel
import com.example.marivramchoir.utlis.ApiState
import com.example.marivramchoir.utlis.CommonMethod
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect


@AndroidEntryPoint
class DashboardActivity : AppCompatActivity() {

    private val dashboardViewModel: DashboardViewModel by viewModels()
    private lateinit var commonMethod: CommonMethod

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        commonMethod = CommonMethod(this)

        if (commonMethod.checkNetworkConnection()) {
            getDataAndInsertIntoDatabase()
        }else{
            commonMethod.showToastMessage("Please Enable Internet Connection")
        }

    }

    private fun getDataAndInsertIntoDatabase() {
        dashboardViewModel.getAllHymens()
        lifecycleScope.launchWhenStarted {
            dashboardViewModel.stateFlowResponse.collect {
                when(it)
                {
                    is ApiState.GetAllHymensSuccess ->
                    {
                        dashboardViewModel.deleteAllHymens()
                        dashboardViewModel.insertAllHymens(it.hymens)
                        commonMethod.showToastMessage("Success")
                    }
                    is ApiState.Failure ->
                    {
                        commonMethod.showToastMessage(it.msg.toString())
                    }
                    else ->
                    {

                    }
                }
            }
        }
    }
}