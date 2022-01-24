package com.example.marivramchoir.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import com.example.marivramchoir.R
import com.example.marivramchoir.ui.viewmodel.AllHymensViewModel
import com.example.marivramchoir.utlis.ApiState
import dmax.dialog.SpotsDialog
import kotlinx.coroutines.flow.collect

class AllHymensActivity : AppCompatActivity() {

    private val allHymensViewModel: AllHymensViewModel by viewModels()
    private var waitingDialog: AlertDialog = TODO()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_hymens)



    }

    override fun onStart() {
        super.onStart()
        allHymensViewModel.getAllHymens()
        lifecycleScope.launchWhenStarted {
            allHymensViewModel.stateFlowData.collect {
                when(it)
                {
                    is ApiState.Loading -> {

                    }
                    is ApiState.GetAllHymensSuccess ->
                    {
                       // commonMethod.showToastMessage("Success")
                    }
                    is ApiState.Failure ->
                    {
                        //commonMethod.showToastMessage(it.msg.toString())
                    }
                    else ->
                    {

                    }
                }
            }
        }
    }
}