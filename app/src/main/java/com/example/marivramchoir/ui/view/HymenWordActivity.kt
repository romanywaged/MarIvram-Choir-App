package com.example.marivramchoir.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.core.app.NavUtils
import com.example.marivramchoir.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HymenWordActivity : AppCompatActivity() {

    private var hymenWord:String = ""
    private var hymenName:String = ""
    private var hymenUrl:String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hymen_word)

        if(intent != null)
        {
            hymenName = intent.getStringExtra("name")!!
            hymenWord = intent.getStringExtra("words")!!
            hymenUrl = intent.getStringExtra("url")!!
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        supportActionBar!!.title = hymenName
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