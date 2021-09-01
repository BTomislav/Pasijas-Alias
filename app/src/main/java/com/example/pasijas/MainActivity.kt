package com.example.pasijas

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    /*var rnd=60
    var score=50
    var exceptions=ArrayList<String>()*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
/*   rnd = 60
  score=50
   Toast.makeText(this,"Prije get:"+rnd+", "+score, Toast.LENGTH_LONG ).show()
   val rek=ActivityCompat.getReferrer(this)
   val extra=intent.extras
   if(extra!=null)
   {
     rnd=extra.getInt("roundtime")
       score=extra.getInt("scoregoal")
       if(rnd==0 && score==0)
       {
           rnd=60
           score=50
       }
   }*/
  // Toast.makeText(this,"Poslje get:"+rnd+", "+score, Toast.LENGTH_LONG ).show()
   newgame.setOnClickListener { v ->
       val intent = Intent(this, Team::class.java)
       /*val bundle = Bundle()
       bundle.putInt("scoregoal", score)
       bundle.putInt("roundtime",rnd)
      bundle.putStringArrayList("exceptions", exceptions)
       intent.putExtras(bundle)*/
       startActivity(intent)
   }
   settings.setOnClickListener {
       val intent = Intent(this, Settings::class.java)
      /* val bundle = Bundle()
       bundle.putInt("scoregoal", score)
       bundle.putInt("roundtime",rnd)
       intent.putExtras(bundle)*/
       startActivity(intent)
       }
   }
}

