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
import com.example.marivramchoir.data.model.Hymn
import com.example.marivramchoir.ui.adapter.HymnAdapter
import com.example.marivramchoir.ui.listener.OnHymnClickListener
import com.example.marivramchoir.ui.viewmodel.AllHymnsViewModel
import com.example.marivramchoir.utlis.ApiState
import com.example.marivramchoir.utlis.CommonMethod
import dagger.hilt.android.AndroidEntryPoint
import dmax.dialog.SpotsDialog
import kotlinx.android.synthetic.main.activity_all_hymns.*
import kotlinx.coroutines.flow.collect
import java.util.*
import kotlin.collections.ArrayList


@AndroidEntryPoint
class AllHymnsActivity : AppCompatActivity() , SearchView.OnQueryTextListener, OnHymnClickListener{

    private val allHymnsViewModel: AllHymnsViewModel by viewModels()
    private lateinit var waitingDialog: AlertDialog
    private lateinit var commonMethod: CommonMethod
    private lateinit var adapter: HymnAdapter
    private var tempHymens = ArrayList<Hymn>()
    private var hymens = ArrayList<Hymn>()
    private var pageTitle:String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_hymns)

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
        allHymnsViewModel.getAllHymensFromDb()
        lifecycleScope.launchWhenStarted {
            allHymnsViewModel.stateFlowData.collect {
                when (it) {
                    is ApiState.Loading -> {
                        waitingDialog.show()
                    }
                    is ApiState.GetAllHymensSuccess -> {
                        hymens.addAll(it.hymns)
                        tempHymens.addAll(it.hymns)
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
        allHymnsViewModel.getAllHymens()
        lifecycleScope.launchWhenStarted {
            allHymnsViewModel.stateFlowResponse.collect {
                when(it)
                {
                    is ApiState.Loading -> {
                        waitingDialog.show()
                    }
                    is ApiState.GetAllHymensSuccess ->
                    {
                        allHymnsViewModel.deleteAllHymens()
                        allHymnsViewModel.insertAllHymens(it.hymns)
                        hymens.addAll(it.hymns)
                        tempHymens.addAll(it.hymns)
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
        adapter = HymnAdapter(this, tempHymens, this)
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

    override fun onHymenClicked(hymn: Hymn) {
        val intent = Intent(this,HymnWordActivity::class.java)
        intent.putExtra("words", hymn.hymenWords)
        intent.putExtra("url", hymn.hymenUrl)
        intent.putExtra("name", hymn.hymenName)
        startActivity(intent)
    }

}