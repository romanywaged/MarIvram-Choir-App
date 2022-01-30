package com.example.marivramchoir.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.marivramchoir.R
import com.example.marivramchoir.data.model.Hymen
import com.example.marivramchoir.ui.viewmodel.AddTarnimaViewModel
import com.example.marivramchoir.utlis.ApiState
import com.example.marivramchoir.utlis.CommonMethod
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_add_tarnima.*
import kotlinx.coroutines.flow.collect


@AndroidEntryPoint
class AddTarnimaActivity : AppCompatActivity() {

    private var pageTitle = ""
    private val addTarnimaViewModel: AddTarnimaViewModel by viewModels()
    private lateinit var commonMethod : CommonMethod

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_tarnima)

        initView()

        addTarnima()

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

        val types = resources.getStringArray(R.array.Albumes)
        val arrayAdapter = ArrayAdapter(this, R.layout.dropdown_items, types)
        add_tarnima_hymen_type.setAdapter(arrayAdapter)
    }

    private fun addTarnima() {
        btn_add_tarnima.setOnClickListener {

            val hymenName = add_tarnima_hymen_name.text
            val hymenUrl = add_tarnima_hymen_url.text
            val hymenType = add_tarnima_hymen_type.text
            val hymenWord = add_tarnima_hymen_words.text

            if (hymenName!!.isEmpty() || hymenUrl!!.isEmpty()
                || hymenType!!.isEmpty() || hymenWord!!.isEmpty())
            {
                commonMethod.showToastMessage("Please Fill All Data")
            }
            else
            {
                val hymen = Hymen()
                hymen.hymenName = hymenName.toString()
                hymen.hymenType = hymenType.toString()
                hymen.hymenUrl = hymenUrl.toString()
                hymen.hymenWords = hymenWord.toString()

                uploadTarnima(hymen)
            }
        }
    }

    private fun uploadTarnima(hymen: Hymen) {
        addTarnimaViewModel.addHymen(hymen)
        lifecycleScope.launchWhenStarted {
            addTarnimaViewModel.stateFlowResponse.collect {
                when(it)
                {
                    is ApiState.Loading ->
                    {
                        btn_add_tarnima.visibility = View.INVISIBLE
                        add_progress.visibility = View.VISIBLE
                        handleViewItems(false)
                    }

                    is ApiState.AddTarnimaSuccess ->
                    {
                        commonMethod.showToastMessage("Added Successfully !!")
                        btn_add_tarnima.visibility = View.VISIBLE
                        add_progress.visibility = View.INVISIBLE
                        handleViewItems(true)
                        add_tarnima_hymen_name.text!!.clear()
                        add_tarnima_hymen_url.text!!.clear()
                        add_tarnima_hymen_type.text!!.clear()
                        add_tarnima_hymen_words.text!!.clear()
                    }

                    is ApiState.Failure ->
                    {
                        commonMethod.showToastMessage(it.msg.toString())
                        btn_add_tarnima.visibility = View.VISIBLE
                        add_progress.visibility = View.INVISIBLE
                        handleViewItems(true)
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
        add_tarnima_hymen_name.isEnabled = enabled
        add_tarnima_hymen_type.isEnabled = enabled
        add_tarnima_hymen_url.isEnabled = enabled
        add_tarnima_hymen_words.isEnabled = enabled
    }

}