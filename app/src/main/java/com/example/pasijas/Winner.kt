package com.example.pasijas

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.KeyEvent
import android.view.LayoutInflater
import android.widget.Button
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_winner.*

class Winner : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_winner)

        val txt=findViewById<TextView>(R.id.txtwinner)
        val btnrestartnew=findViewById<Button>(R.id.restartnew)
        val btnrestartsame=findViewById<Button>(R.id.restartsame)

        for (i in 0 until Team.names.size){
            if (Team.names[i]==(TeamScores.winnerid).toString())
            {
                txt.setText(Team.names[i+1]+" WON!!!")
            }
        }
        btnrestartnew.setOnClickListener {
            Team.names.clear()
            Team.id=0
            PlayScreen1.trackTeamId=0
            PlayScreen1.playercheck=0
            val intent_restart_new=Intent(this, Team::class.java)
            startActivity(intent_restart_new)
        }
        btnrestartsame.setOnClickListener {
            PlayScreen1.trackTeamId=0
            PlayScreen1.playercheck=0
            for (i in 0 until Results.teamScores.size step 1)
            {
                Results.teamScores[i]=0
            }
            val intent_restart_same=Intent(this, PlayScreen1::class.java)
            startActivity(intent_restart_same)
        }
    }
}
