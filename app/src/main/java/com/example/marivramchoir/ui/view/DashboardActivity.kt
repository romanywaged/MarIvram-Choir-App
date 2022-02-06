package com.example.marivramchoir.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.marivramchoir.R
import com.example.marivramchoir.utlis.CommonMethod
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_dashboard.*


@AndroidEntryPoint
class DashboardActivity : AppCompatActivity() {

    private lateinit var commonMethod: CommonMethod

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        commonMethod = CommonMethod(this)

        taranem_card_view.setOnClickListener {
            navigateActivity(AllHymnsActivity::class.java, "Tranim")
        }

        add_tranim_card.setOnClickListener {
            navigateActivity(AddTarnimaActivity::class.java, "Add Tarnima")
        }

        album_category_card.setOnClickListener {
            navigateActivity(AlbumsActivity::class.java, " ")
        }

        create_servant_card.setOnClickListener {
            navigateActivity(CreateServantActivity::class.java, "Create Servant")
        }

    }


    private fun navigateActivity(activity : Class<*>?, pageTitle:String)
    {
        intent = Intent(this, activity)
        intent.putExtra("title", pageTitle)
        startActivity(intent)
    }
}