package com.example.marivramchoir.ui.view

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.marivramchoir.R
import com.example.marivramchoir.data.model.Hymen
import com.example.marivramchoir.ui.adapter.HymenAdapter
import com.example.marivramchoir.ui.listener.OnHymenClickListener
import com.example.marivramchoir.ui.viewmodel.AllHymensViewModel
import com.example.marivramchoir.utlis.ApiState
import com.example.marivramchoir.utlis.CommonMethod
import dagger.hilt.android.AndroidEntryPoint
import dmax.dialog.SpotsDialog
import kotlinx.android.synthetic.main.activity_all_hymens.*
import kotlinx.coroutines.flow.collect
import java.util.*
import kotlin.collections.ArrayList


@AndroidEntryPoint
class AllHymensActivity : AppCompatActivity() , SearchView.OnQueryTextListener, OnHymenClickListener{

    private val allHymensViewModel: AllHymensViewModel by viewModels()
    private lateinit var waitingDialog: AlertDialog
    private lateinit var commonMethod: CommonMethod
    private lateinit var adapter: HymenAdapter
    private var tempHymens = ArrayList<Hymen>()
    private var hymens = ArrayList<Hymen>()
    private var pageTitle:String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_hymens)

        commonMethod = CommonMethod(this)

        if (commonMethod.checkNetworkConnection()) {
            getDataAndInsertIntoDatabase()
        }else{
            loadFromDb()
        }
        waitingDialog = SpotsDialog.Builder().setContext(this)
            .setTheme(R.style.Custom)
            .build()
       if(intent != null)
       {
            pageTitle = intent.getStringExtra("title")!!
       }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        supportActionBar!!.title = pageTitle

    }

    private fun loadFromDb()
    {
        allHymensViewModel.getAllHymensFromDb()
        lifecycleScope.launchWhenStarted {
            allHymensViewModel.stateFlowData.collect {
                when (it) {
                    is ApiState.Loading -> {
                        waitingDialog.show()
                    }
                    is ApiState.GetAllHymensSuccess -> {
                        hymens.addAll(it.hymens)
                        tempHymens.addAll(it.hymens)
                        commonMethod.showToastMessage("Success")
                        setupRecycle()
                        waitingDialog.dismiss()
                    }
                    is ApiState.Failure -> {
                        commonMethod.showToastMessage(it.msg.toString())
                        waitingDialog.dismiss()
                    }
                    is ApiState.Empty -> {
                        commonMethod.showToastMessage("Empty")
                        waitingDialog.dismiss()
                    }
                }
            }
        }
    }

    private fun getDataAndInsertIntoDatabase() {
        allHymensViewModel.getAllHymens()
        lifecycleScope.launchWhenStarted {
            allHymensViewModel.stateFlowResponse.collect {
                when(it)
                {
                    is ApiState.Loading -> {
                        waitingDialog.show()
                    }
                    is ApiState.GetAllHymensSuccess ->
                    {
                        allHymensViewModel.deleteAllHymens()
                        allHymensViewModel.insertAllHymens(it.hymens)
                        hymens.addAll(it.hymens)
                        tempHymens.addAll(it.hymens)
                        commonMethod.showToastMessage("Success")
                        setupRecycle()
                        waitingDialog.dismiss()
                    }
                    is ApiState.Failure ->
                    {
                        commonMethod.showToastMessage(it.msg.toString())
                        waitingDialog.dismiss()
                    }
                    is ApiState.Empty ->
                    {
                        commonMethod.showToastMessage("Empty")
                    }
                }
            }
        }

    }

    private fun setupRecycle() {
        adapter = HymenAdapter(this, tempHymens, this)
        Rv_hymens.layoutManager = LinearLayoutManager(this)
        Rv_hymens.setHasFixedSize(true)
        Rv_hymens.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)

        val search = menu.findItem(R.id.search_app)
        val searchView = search?.actionView as? SearchView
        searchView?.isSubmitButtonEnabled = true
        searchView?.setOnQueryTextListener(this)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return true
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onQueryTextChange(newText: String?): Boolean {
        tempHymens.clear()

        val text = newText!!.lowercase(Locale.getDefault())
        if (text.isNotEmpty())
        {
            hymens.forEach {
                if (it.hymenName!!.lowercase(Locale.getDefault()).contains(text))
                {
                    tempHymens.add(it)
                }
                Rv_hymens.adapter!!.notifyDataSetChanged()
            }
        }
        else
        {
            tempHymens.clear()
            tempHymens.addAll(hymens)
            Rv_hymens.adapter!!.notifyDataSetChanged()
        }
        return false
    }

    override fun onHymenClicked(hymen: Hymen) {
        val intent = Intent(this,HymenWordActivity::class.java)
        intent.putExtra("words", hymen.hymenWords)
        intent.putExtra("url", hymen.hymenUrl)
        intent.putExtra("name", hymen.hymenName)
        startActivity(intent)
    }

}