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

        //Toast.makeText(this,"Prije get:"+roundget+", "+scoreget, Toast.LENGTH_LONG ).show()
       /* val extra=intent.extras
        roundget=extra.getInt("roundtime")
        scoreget=extra.getInt("scoregoal")*/
        //Toast.makeText(this,"Poslje get:"+roundget+", "+scoreget, Toast.LENGTH_LONG ).show()

        val rnd=this.findViewById<EditText>(R.id.roundtime)
        val score=this.findViewById<EditText>(R.id.scoregoal)

        rnd.setText(Settings.roundtime.toString())
        score.setText(Settings.scorecap.toString())

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
                Settings.roundtime=rnd.text.toString().toInt()

            }
            if(score.text.toString().toInt()<10 || score.text.toString().toInt()>100 || score.text==null)
            {
                Toast.makeText(this, "Minimum score goal is 10 and maximum is 100",Toast.LENGTH_SHORT).show()
                score.setText("50")
            }
            else{
                Settings.scorecap=score.text.toString().toInt()
            }
        }

        //Settings()
            }



   /* private fun Settings() {
        error=0
        val btn=this.findViewById<Button>(R.id.buttonsettings)
        btn.setOnClickListener {
            Save()
        }
    }*/
       /* private fun Save() {
        val rnd=this.findViewById<EditText>(R.id.roundtime)
        val score=this.findViewById<EditText>(R.id.scoregoal)
        if(rnd.text.toString().toInt()<5 || rnd.text.toString().toInt()>100)
        {
            Toast.makeText(this, "Minimum round time is 30 and maximum is 100",Toast.LENGTH_SHORT).show()
            rnd.setText("60")
            error=1
        }
        if(score.text.toString().toInt()<10 || score.text.toString().toInt()>100)
        {
            Toast.makeText(this, "Minimum score goal is 10 and maximum is 100",Toast.LENGTH_SHORT).show()
            score.setText("50")
            error=1
        }
        if(error==1)
        {
            Settings()
        }
        else if (error==0){
            val intent2=Intent(this, MainActivity::class.java)
            Settings.roundget=rnd.text.toString().toInt()
            Settings.scoreget=score.text.toString().toInt()
            /*val roundsend=rnd.text.toString().toInt()
            val scoresend=score.text.toString().toInt()*/
            /*val bundle=Bundle()
            bundle.putInt("scoregoal", score.text.toString().toInt())
            bundle.putInt("roundtime",rnd.text.toString().toInt())
            intent2.putExtras(bundle)*/
            startActivity(intent2)
        }
    }*/
}


