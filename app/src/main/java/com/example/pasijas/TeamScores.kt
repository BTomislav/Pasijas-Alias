package com.example.pasijas

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.KeyEvent
import android.view.LayoutInflater
import android.widget.*
import kotlinx.android.synthetic.main.activity_results.*
import kotlinx.android.synthetic.main.activity_team_scores.*

class TeamScores : AppCompatActivity() {

    companion object {
        var winnerid=0
        var postCheck=false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_scores)
       /* val extra=intent.extras
        var exceptions=extra.getStringArrayList("exceptions")
        var roundtimeget=extra.getInt("roundtime")
        var scoregoal=extra.getInt("scoregoal")
        var id=extra.getInt("id")
        var names=extra.getStringArray("names")
        var wcount=extra.getInt("wcount")
        var rcount=extra.getInt("rcount")
        var trackteam=extra.getInt("trackteam")
        var teamscore=extra.getIntArray("teamscores")
        var playercheck=extra.getInt("playercheck")*/


        if(!postCheck){
            Results.teamScores[PlayScreen1.trackTeamId]+=(PlayScreen1.rightAnswerCounter-PlayScreen1.wrongAnswerCounter)
        }

        val lvts=findViewById<ListView>(R.id.lvts)
        val btncontinue=findViewById<Button>(R.id.buttoncontinue)
        val li = ArrayList<String>(0)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, li)
        lvts.adapter=adapter
        var innertrack=0
        for(i in 1 until Team.id+1 step 1){
            adapter.add(Team.names[innertrack+1].toUpperCase()+" ("+Team.names[innertrack+2]+", "+Team.names[innertrack+3]+"):   "+Results.teamScores[i-1])
            innertrack+=4
        }

        //trackteam stari
        var winnercheck=0
         btncontinue.setOnClickListener {
          for (i in 0 until Results.teamScores.size){
              if (Results.teamScores[i]>=Settings.scorecap){
                  winnercheck++
                  winnerid=i
              }
          }
          if (winnercheck>0){
              val intent_winner=Intent(this, Winner::class.java)
              /*val bundle = Bundle()
              bundle.putInt("scoregoal", scoregoal)
              bundle.putInt("roundtime",roundtimeget)
              bundle.putInt("id",id)
              bundle.putInt("trackteam",trackteam)
              bundle.putStringArray("names",names)
              bundle.putStringArrayList("exceptions",exceptions)
              bundle.putIntArray("teamscores", teamscore)
              bundle.putInt("playercheck", playercheck)
              bundle.putInt("winner", winnerid)
              intent_winner.putExtras(bundle)*/
              startActivity(intent_winner)
          }
          else{
              val intentplay=Intent(this, PlayScreen1::class.java)
              PlayScreen1.trackTeamId++
              TeamScores.postCheck=false
              /*val bundle = Bundle()
              bundle.putInt("scoregoal", scoregoal)
              bundle.putInt("roundtime",roundtimeget)
              bundle.putInt("id",id)
              bundle.putInt("trackteam",trackteam)
              bundle.putStringArray("names",names)
              bundle.putStringArrayList("exceptions",exceptions)
              bundle.putIntArray("teamscores", teamscore)
              bundle.putInt("playercheck", playercheck)
              intentplay.putExtras(bundle)*/
              startActivity(intentplay)
          }
        }
    }
   override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            TeamScores.postCheck=true
        }
        return super.onKeyDown(keyCode, event)
    }
}