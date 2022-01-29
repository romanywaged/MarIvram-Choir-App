package com.example.marivramchoir.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.marivramchoir.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_hymen_word.*

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

        words.text = hymenWord
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