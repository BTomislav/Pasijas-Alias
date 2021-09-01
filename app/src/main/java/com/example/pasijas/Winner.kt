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

        //EXTRAS
       /* val extra=intent.extras
        var exceptions=extra.getStringArrayList("exceptions")
        var roundtimeget=extra.getInt("roundtime")
        var scoregoal=extra.getInt("scoregoal")
        var id=extra.getInt("id")
        var names=extra.getStringArray("names")
        var trackteam=extra.getInt("trackteam")
        var teamscore=extra.getIntArray("teamscores")
        var playercheck=extra.getInt("playercheck")
        var winner=extra.getInt("winner")*/

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
            /*val bundle = Bundle()
            bundle.putInt("scoregoal", scoregoal)
            bundle.putInt("roundtime",roundtimeget)
            bundle.putStringArrayList("exceptions",exceptions)
            intent_restart_new.putExtras(bundle)*/
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
           /* val bundle = Bundle()
            bundle.putInt("scoregoal", scoregoal)
            bundle.putInt("roundtime",roundtimeget)
            bundle.putInt("id",id)
            bundle.putInt("trackteam",trackteam)
            bundle.putStringArray("names",names)
            bundle.putStringArrayList("exceptions",exceptions)
            bundle.putIntArray("teamscores", teamscore)
            bundle.putInt("playercheck", playercheck)
            intent_restart_same.putExtras(bundle)*/
            startActivity(intent_restart_same)
        }
    }
    /*override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            val inflater: LayoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val view2 = inflater.inflate(R.layout.popupexit, null)
            val yesbutton=view2.findViewById<Button>(R.id.yesbutton)
            val nobutton=view2.findViewById<Button>(R.id.nobutton)
            val popupWindow2 = PopupWindow(
                view2,
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            popupWindow2.setFocusable(true)
            popupWindow2.update()
            popupWindow2.showAtLocation(txtwinner, Gravity.CENTER, 0, 1)
            yesbutton.setOnClickListener {
                val intent2= Intent(this, MainActivity::class.java)
                startActivity(intent2)
            }
            nobutton.setOnClickListener {
                popupWindow2.dismiss()
            }

        }
        return super.onKeyDown(keyCode, event)
    }*/
}
