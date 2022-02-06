package com.example.marivramchoir.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.marivramchoir.R
import kotlinx.android.synthetic.main.activity_albums_activity.*

class AlbumsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_albums_activity)

        first_album.setOnClickListener {
            navigateActivity(resources.getString(R.string.first_album))
        }

        second_album.setOnClickListener {
            navigateActivity(resources.getString(R.string.second_album))
        }

        third_album.setOnClickListener {
            navigateActivity(resources.getString(R.string.third_album))
        }

        fourth_album.setOnClickListener {
            navigateActivity(resources.getString(R.string.fourth_album))
        }

        fifth_album.setOnClickListener {
            navigateActivity(resources.getString(R.string.fifth_album))
        }

        arrow_back.setOnClickListener {
            onBackPressed()
        }

    }

    private fun navigateActivity(type:String)
    {
        intent = Intent(this, AllHymnsInAlbumActivity::class.java)
        intent.putExtra("Album_Type", type)
        startActivity(intent)
    }
}