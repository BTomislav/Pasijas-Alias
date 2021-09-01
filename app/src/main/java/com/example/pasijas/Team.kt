package com.example.pasijas

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.transition.Slide
import android.transition.TransitionManager
import android.widget.*
import kotlinx.android.synthetic.main.activity_settings.*
import kotlinx.android.synthetic.main.activity_team.*
import kotlinx.android.synthetic.main.popup.*
import android.support.v4.app.NotificationCompat.getExtras
import android.content.Intent
import android.view.*


class Team : AppCompatActivity() {

    companion object {
        var names = ArrayList<String>()
        var delete=ArrayList<String>()
        var listItemsBackup = ArrayList<String>()
        var id=0
    }
    var track = 0
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team)

       /* val extra=intent.extras
        val roundget=extra.getInt("roundtime")
        val scoreget=extra.getInt("scoregoal")*/
        //EXTRAS default
        /* var exceptions=extra.getStringArrayList("exceptions")
         var teamscore=IntArray(6)*/


        var listItems = ArrayList<String>()
        if (names.isEmpty()){
        }
        else{
           listItems=listItemsBackup
        }
        val lv = findViewById<ListView>(R.id.listview)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems)
        lv.adapter = adapter




        buttonPlay.setOnClickListener { v ->
            if(id<2)
            {
                Toast.makeText(this, "There must be at least 2 teams to continue", Toast.LENGTH_SHORT).show()
            }
            else{
                for (i in 0 until Results.teamScores.size step 1){
                    Results.teamScores[i]=0
                }
                PlayScreen1.trackTeamId=0
                val playintent=Intent(this,PlayScreen1::class.java)
                /*val bundle = Bundle()
                bundle.putInt("scoregoal", scoreget)
                bundle.putInt("roundtime",roundget)
                bundle.putInt("id",id)
                bundle.putStringArray("names",names)
                bundle.putStringArrayList("exceptions",exceptions)
                bundle.putIntArray("teamscores", teamscore)
                playintent.putExtras(bundle)*/
                startActivity(playintent)
            }
        }


        buttonAddTeam.setOnClickListener { v ->
            val inflater: LayoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val view = inflater.inflate(R.layout.popup, null)
            val popupWindow = PopupWindow(
                view,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            popupWindow.setFocusable(true)
            popupWindow.update()

            val teamName = view.findViewById<EditText>(R.id.teamname)
            val playerOneName = view.findViewById<EditText>(R.id.player1name)
            val playerTwoName = view.findViewById<EditText>(R.id.player2name)
            val buttonPopupConfirm = view.findViewById<Button>(R.id.button_popup)

            buttonPopupConfirm.setOnClickListener {
                if(teamName.text.toString()=="" || playerOneName.text.toString()=="" || playerTwoName.text.toString()=="")
                {
                    Toast.makeText(this, "Fields can't be empty", Toast.LENGTH_SHORT).show()
                }
                else if(teamName.text.toString()!="" && playerOneName.text.toString()!="" && playerTwoName.text.toString()!=""){
                    /*names[track]=id.toString()
                    names[track+1] = team.text.toString()
                    names[track + 2] = p1.text.toString()
                    names[track + 3] = p2.text.toString()
                    track += 4
                    id++*/
                    names.add(id.toString())
                    names.add(teamName.text.toString())
                    names.add(playerOneName.text.toString())
                    names.add(playerTwoName.text.toString())
                    track+=4
                    id++
                    adapter.add(teamName.text.toString().toUpperCase() + ":\n" + playerOneName.text.toString() + ", " + playerTwoName.text.toString())
                    listItemsBackup=listItems
                    popupWindow.dismiss()
                }
            }
            popupWindow.showAtLocation(toolbar2, Gravity.CENTER, 0, 1)
            /*if(track>=24)
        {
            Toast.makeText(this, "You've reached max number of teams", Toast.LENGTH_LONG).show()
            popupWindow.dismiss()
        }*/
        }


        lv.setOnItemClickListener { parent, view, position, id ->
            /*for(i in 0 until 21 step 1){
                if (names[i]==position.toString()){
                    Toast.makeText(this, "ID je: "+names[i]+", a ime tima je: "+names[i+1], Toast.LENGTH_LONG).show()
                }
            }*/

            val inflater: LayoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val view2 = inflater.inflate(R.layout.popup2, null)

            val popupWindow2 = PopupWindow(
                view2,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            popupWindow2.setFocusable(true)
            popupWindow2.update()
        val teamName = view2.findViewById<EditText>(R.id.rename_teamname)
        val playerOneName = view2.findViewById<EditText>(R.id.rename_player1name)
        val playerTwoName = view2.findViewById<EditText>(R.id.rename_player2name)
        val rename = view2.findViewById<Button>(R.id.button_rename)
        val del = view2.findViewById<Button>(R.id.delete)

            for(i in 0 until names.size step 4){
                if (names[i]==position.toString()){
                    teamName.setText(names[i+1])
                    playerOneName.setText(names[i+2])
                    playerTwoName.setText(names[i+3])
                }
            }

            rename.setOnClickListener{
                if(teamName.text.toString()=="" || playerOneName.text.toString()=="" || playerTwoName.text.toString()=="")
                {
                    Toast.makeText(this, "Fields can't be empty", Toast.LENGTH_SHORT).show()
                }
                else if(teamName.text.toString()!="" && playerOneName.text.toString()!="" && playerTwoName.text.toString()!=""){
                    adapter.remove(adapter.getItem(position))
                    for(i in 0 until names.size step 4){
                        if (names[i]==position.toString()){
                            names[i]=position.toString()
                            names[i+1]=teamName.text.toString()
                            names[i+2]=playerOneName.text.toString()
                            names[i+3]=playerTwoName.text.toString()
                        }
                    }
                    adapter.insert(teamName.text.toString().toUpperCase() + ":\n" + playerOneName.text.toString() + ", " + playerTwoName.text.toString(), position)
                    popupWindow2.dismiss()
                }

            }

       del.setOnClickListener {
           adapter.remove(adapter.getItem(position))
           track-=4
           for (i in 0 until names.size step 4){
               if (names[i]==position.toString()){
                   delete.add(names[i])
                   delete.add(names[i+1])
                   delete.add(names[i+2])
                   delete.add(names[i+3])
               }
               }
           names.removeAll(delete)
           delete.clear()
           for (i in 0 until names.size step 4){
               if(names[i].toInt()==position+1){

                   for(j in i until names.size step 4){
                       names[j]=(names[j].toInt()-1).toString()
                       //Toast.makeText(this, names[i], Toast.LENGTH_SHORT).show()
                   }
               }
               //names[i]=(names[i].toInt()-1).toString()
               }
          /* track-=4
                for (i in 0 until 18 )
                {
                    if (names[i]==position.toString())
                    {
                        if(i==20)
                        {
                        }
                        else{
                            for (j in i until 20 step 1){
                                if(j==0 || j==4 || j==8 || j==12 || j==16)
                                {

                                }
                                else
                                {
                                    names[j]=names[j+4]
                                }

                            }
                        }
                        for(o in 20 until 24)
                        {
                            names[o]= null.toString()
                        }
                    }
                }*/
           Team.id -= 1
               popupWindow2.dismiss()
           //Toast.makeText(this, names[5]+"ID je"+names[4], Toast.LENGTH_LONG).show()

       }
            popupWindow2.showAtLocation(toolbar2, Gravity.CENTER, 0, 1)
        }
    }
    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

                val intent2= Intent(this, MainActivity::class.java)
                startActivity(intent2)
            }
        return super.onKeyDown(keyCode, event)
    }
}



