package com.example.pasijas

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class Settings : AppCompatActivity() {

    companion object {
        var  roundtime=60
        var scorecap=50
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val rnd=this.findViewById<EditText>(R.id.roundtime)
        val score=this.findViewById<EditText>(R.id.scoregoal)

        rnd.setText(roundtime.toString())
        score.setText(scorecap.toString())

        val btn=this.findViewById<Button>(R.id.buttonsettings)
        btn.setOnClickListener {
            if(rnd.text.isNullOrEmpty()){
                rnd.setText("60")
            }
            if(score.text.isNullOrEmpty()){
                score.setText("50")
            }
            if(rnd.text.toString().toInt()<5 || rnd.text.toString().toInt()>100 || score.text==null)
            {
                Toast.makeText(this, "Minimum round time is 30 and maximum is 100",Toast.LENGTH_SHORT).show()
                rnd.setText("60")

            }
            else{
                roundtime=rnd.text.toString().toInt()

            }
            if(score.text.toString().toInt()<10 || score.text.toString().toInt()>100 || score.text==null)
            {
                Toast.makeText(this, "Minimum score goal is 10 and maximum is 100",Toast.LENGTH_SHORT).show()
                score.setText("50")
            }
            else{
                scorecap=score.text.toString().toInt()
            }
        }
            }
}


