package com.example.marivramchoir.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.marivramchoir.R
import com.example.marivramchoir.data.model.Servant
import com.example.marivramchoir.ui.viewmodel.CreateServantViewModel
import com.example.marivramchoir.utlis.ApiState
import com.example.marivramchoir.utlis.CommonMethod
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_create_servant.*
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class CreateServantActivity : AppCompatActivity() {

    private val createServantViewModel: CreateServantViewModel by viewModels()
    private var pageTitle = ""
    private lateinit var commonMethod:CommonMethod


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_servant)


        initView()

    }

    private fun initView()
    {
        commonMethod = CommonMethod(this)


        if (intent != null)
        {
            pageTitle = intent.getStringExtra("title")!!
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        supportActionBar!!.title = pageTitle

        btn_create_servant.setOnClickListener {
            createServant()
        }

    }

    private fun createServant() {
        val servantName = create_servant_name.text!!
        val servantDate = create_servant_date.text!!
        val servantLocation = create_servant_location.text!!

        if (servantName.isEmpty() || servantDate.isEmpty() || servantLocation.isEmpty())
        {
            commonMethod.showToastMessage("Please Fill All Data")
        }
        else
        {
            val servant = Servant()
            servant.servantName = servantName.toString()
            servant.servantDate = servantDate.toString()
            servant.servantLocation = servantLocation.toString()

            uploadServant(servant)
        }
    }

    private fun uploadServant(servant: Servant) {
        createServantViewModel.createServant(servant)
        lifecycleScope.launchWhenStarted {
            createServantViewModel.stateFlowResponse.collect {
                when (it)
                {
                    is ApiState.Loading ->
                    {
                        btn_create_servant.visibility = View.INVISIBLE
                        create_servant_progress.visibility = View.VISIBLE
                        handleViewItems(false)
                    }

                    is ApiState.Empty ->
                    {
                        btn_create_servant.visibility = View.VISIBLE
                        create_servant_progress.visibility = View.INVISIBLE
                        handleViewItems(true)
                        commonMethod.showToastMessage("Empty !")
                    }

                    is ApiState.Failure ->
                    {
                        btn_create_servant.visibility = View.VISIBLE
                        create_servant_progress.visibility = View.INVISIBLE
                        handleViewItems(true)
                        commonMethod.showToastMessage(it.msg.toString())
                    }

                    is ApiState.AddServantSuccess ->
                    {
                        btn_create_servant.visibility = View.INVISIBLE
                        create_servant_progress.visibility = View.VISIBLE
                        handleViewItems(true)
                        commonMethod.showToastMessage("Added Successfully !")
                    }
                    else ->
                    {

                    }
                }
            }
        }
    }

    private fun handleViewItems(enabled: Boolean)
    {
        create_servant_name.isEnabled = enabled
        create_servant_date.isEnabled = enabled
        create_servant_location.isEnabled = enabled
    }

}
