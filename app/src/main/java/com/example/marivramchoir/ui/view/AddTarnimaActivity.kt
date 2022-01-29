package com.example.marivramchoir.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.marivramchoir.R


class AddTarnimaActivity : AppCompatActivity() {

    private var pageTitle = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_tarnima)

        if (intent != null)
        {
            pageTitle = intent.getStringExtra("title")!!
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        supportActionBar!!.title = pageTitle

    }
}