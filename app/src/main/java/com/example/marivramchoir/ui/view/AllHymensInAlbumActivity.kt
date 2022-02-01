package com.example.marivramchoir.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.marivramchoir.R
import com.example.marivramchoir.data.model.Hymen
import com.example.marivramchoir.ui.adapter.HymenAdapter
import com.example.marivramchoir.ui.listener.OnHymenClickListener
import com.example.marivramchoir.ui.viewmodel.AllHymensByAlbumViewModel
import com.example.marivramchoir.utlis.ApiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_all_hymens_in_album.*
import kotlinx.coroutines.flow.collect


@AndroidEntryPoint
class AllHymensInAlbumActivity : AppCompatActivity() , OnHymenClickListener{

    private val allHymensByAlbumViewModel: AllHymensByAlbumViewModel by viewModels()
    private lateinit var type:String

    private lateinit var adapter: HymenAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_hymens_in_album)

        if (intent != null)
        {
            type = intent.getStringExtra("Album_Type")!!
            getHymensData(type)
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        supportActionBar!!.title = type

    }

    private fun getHymensData(type:String) {
        allHymensByAlbumViewModel.getAllHymensByAlbum(type)
        lifecycleScope.launchWhenStarted {
            allHymensByAlbumViewModel.stateFlowData.collect {
                when (it)
                {
                    is ApiState.Loading ->
                    {

                    }

                    is ApiState.GetAllHymensSuccess ->
                    {
                        setUpRecycle(it.hymens)
                    }

                    is ApiState.Empty ->
                    {
                        no_data_txt.visibility = View.VISIBLE
                        all_hymens_in_album.visibility = View.INVISIBLE
                    }

                    is ApiState.Failure ->
                    {
                        no_data_txt.visibility = View.VISIBLE
                        all_hymens_in_album.visibility = View.INVISIBLE
                    }

                    else ->
                    {

                    }
                }
            }
        }
    }

    private fun setUpRecycle(hymens: List<Hymen>) {
        adapter = HymenAdapter(this, hymens, this)
        all_hymens_in_album.layoutManager = LinearLayoutManager(this)
        all_hymens_in_album.setHasFixedSize(true)
        all_hymens_in_album.adapter = adapter
    }

    override fun onHymenClicked(hymen: Hymen) {
        val intent = Intent(this,HymenWordActivity::class.java)
        intent.putExtra("words", hymen.hymenWords)
        intent.putExtra("url", hymen.hymenUrl)
        intent.putExtra("name", hymen.hymenName)
        startActivity(intent)
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

}