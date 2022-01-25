package com.example.marivramchoir.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.marivramchoir.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*


@AndroidEntryPoint
class DashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        taranem_card_view.setOnClickListener {
            navigateActivity(AllHymensActivity::class.java, "Tranim")
        }

        addTranim_card.setOnClickListener {
            navigateActivity(AddTarnimaActivity::class.java, "Add Tarnima")
        }

    }



    private fun navigateActivity(activity : Class<*>?, pageTitle:String)
    {
        intent = Intent(this, activity)
        intent.putExtra("title", pageTitle)
        startActivity(intent)

    }

}