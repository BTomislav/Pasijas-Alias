package com.example.pasijas

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.os.CountDownTimer
import android.os.Handler
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.*
import kotlinx.android.synthetic.main.activity_play_screen1.*
import kotlinx.android.synthetic.main.activity_team.*
import java.io.*

class PlayScreen1 : AppCompatActivity() {

    var line=""
    var random=0
    companion object {
        var exceptionList = ArrayList<String>()
        val wrongList = ArrayList<String>()
        val rightList = ArrayList<String>()
        var wrongAnswerCounter=0
        var rightAnswerCounter=0
        var playercheck=0 //checks which player 1/2 should play
        var trackTeamId=0
    }

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play_screen1)
        val word=findViewById<TextView>(R.id.word)
        val time=findViewById<TextView>(R.id.timeshow)
        val playbtn=findViewById<Button>(R.id.startbutton)

        time.setText(Settings.roundtime.toString())

        if(trackTeamId==Team.id){
            trackTeamId=0
            playercheck++
        }

        for(i in 0 until Team.names.size step 4){
            if(Team.names[i]== trackTeamId.toString()){
                if (playercheck%2==0){
                    word.setText("Reading: "+Team.names[i+2].toString()+"\nGuessing: "+Team.names[i+3].toString())
                }
                else
                {
                    word.setText("Reading: "+Team.names[i+3].toString()+"\nGuessing: "+Team.names[i+2].toString())
                }
            }
        }

        playbtn.setOnClickListener {
            playbtn.setVisibility(View.GONE)
            wrongList.clear()
            rightList.clear()
            wrongAnswerCounter=0
            rightAnswerCounter=0
            play()
        }
    }

    private fun play() {
        val time=findViewById<TextView>(R.id.timeshow)
        val wbutton=findViewById<ImageButton>(R.id.wrongbutton)
        val rbutton=findViewById<ImageButton>(R.id.rightbutton)
        val wrongcounter=findViewById<TextView>(R.id.wrongshow)
        val rightcounter=findViewById<TextView>(R.id.rightshow)
        object : CountDownTimer(Settings.roundtime.toLong()*1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                time.setText((millisUntilFinished / 1000).toString())
                if (millisUntilFinished/1000<11)
                {
                    time.setTextColor(Color.parseColor("#ff0000"))
                }
            }
            override fun onFinish() {
                time.setText("0")
                word.setTextColor(Color.parseColor("#ff0000"))
                word.setText("TIME'S UP")
                wbutton.isClickable=false
                rbutton.isClickable=false
                Handler().postDelayed({
                    val intentresult= Intent(this@PlayScreen1, Results::class.java)
                    startActivity(intentresult)
                    finish()
                }, 2000)

            }
        }.start()

        val file_name = "listofwords.txt"
        var read = application.assets.open(file_name).bufferedReader()
        read.readLine()
        read.mark(1000)
        randomfunction(read)

        wbutton.setOnClickListener {
            wrongAnswerCounter++
            wrongList.add(line)
            wrongcounter.setText(wrongAnswerCounter.toString())
            randomfunction(read)

        }

        rbutton.setOnClickListener {
            rightAnswerCounter++
            rightList.add(line)
            rightcounter.setText(rightAnswerCounter.toString())
            randomfunction(read)
        }
        }

    private fun randomfunction(read: BufferedReader) {

        if(exceptionList.size>118)
        {
            exceptionList.clear()
        }
        random = rnd()

        if(exceptionList.contains(random.toString()))
        {
            randomfunction(read)
        }
        else{
            exceptionList.add(random.toString())
            for (i in 0 until random)
            {
                line=read.readLine()
            }
            read.reset()
            word.setText(line)
        }
    }

    private fun rnd(): Int{
        return (1..120).random()
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
            popupWindow2.showAtLocation(timeshow, Gravity.CENTER, 0, 1)
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




