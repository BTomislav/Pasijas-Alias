package com.example.pasijas

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.KeyEvent
import android.view.LayoutInflater
import android.widget.*
import kotlinx.android.synthetic.main.activity_play_screen1.*
import kotlinx.android.synthetic.main.activity_results.*

class Results : AppCompatActivity() {

    companion object {
        var teamScores=IntArray(Team.id+1)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results)
       /* val right: ArrayList<String>
        val wrong: ArrayList<String>
        val roundtimeget: Int
        val scoregoal: Int
        val id: Int
        val names: Array<String>
        val wcount: Int=0
        val rcount: Int=0*/

        /*val extra=intent.extras
        var right=extra.getStringArrayList("right")
        var wrong=extra.getStringArrayList("wrong")
        var exceptions=extra.getStringArrayList("exceptions")
        var roundtimeget=extra.getInt("roundtime")
        var scoregoal=extra.getInt("scoregoal")
        var id=extra.getInt("id")
        var names=extra.getStringArray("names")
        var wcount=extra.getInt("wcount")
        var rcount=extra.getInt("rcount")
        var trackteam=extra.getInt("trackteam")
        var teamscores=extra.getIntArray("teamscores")
        var playercheck=extra.getInt("playercheck")*/


        var test=0
        val list=findViewById<ListView>(R.id.list)
        val buttonShow=findViewById<Button>(R.id.buttonrw)
        val buttonContinue=findViewById<Button>(R.id.buttoncontinue)
        val li = ArrayList<String>(0)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, li)
        list.adapter=adapter
        PlayScreen1.rightList.forEach(){
            adapter.add(it)
        }
        buttonShow.setOnClickListener {
            if (test==0){
                adapter.clear()
                PlayScreen1.wrongList.forEach(){
                    adapter.add(it)
                }
                buttonrw.setText("SHOW RIGHT GUESSES")
                test=1
            }
            else{
                adapter.clear()
                PlayScreen1.rightList.forEach(){
                    adapter.add(it)
                }
                buttonrw.setText("SHOW WRONG GUESSES")
                test=0
            }
        }
        buttonContinue.setOnClickListener {
            val intent_teamscores=Intent(this, TeamScores::class.java)
            /*val bundle = Bundle()
            bundle.putInt("scoregoal", scoregoal)
            bundle.putInt("roundtime",roundtimeget)
            bundle.putInt("id",id)
            bundle.putInt("trackteam",trackteam)
            bundle.putInt("wcount",wcount)
            bundle.putInt("rcount",rcount)
            bundle.putStringArray("names",names)
            bundle.putStringArrayList("exceptions",exceptions)
            bundle.putIntArray("teamscores", teamscores)
            bundle.putInt("playercheck", playercheck)
            intent_teamscores.putExtras(bundle)*/
            startActivity(intent_teamscores)
        }
        }
    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
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
            popupWindow2.showAtLocation(toolbar3, Gravity.CENTER, 0, 1)
            yesbutton.setOnClickListener {
                val intent2= Intent(this, MainActivity::class.java)
                startActivity(intent2)
            }
            nobutton.setOnClickListener {
                popupWindow2.dismiss()
            }

        }
        return super.onKeyDown(keyCode, event)
    }
    }

