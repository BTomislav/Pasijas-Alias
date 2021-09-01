package com.example.pasijas

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
   newgame.setOnClickListener { v ->
       val intent = Intent(this, Team::class.java)
       startActivity(intent)
   }
   settings.setOnClickListener {
       val intent = Intent(this, Settings::class.java)
       startActivity(intent)
       }
   }
}

