package com.example.ma23_android_project_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class GameActivity : AppCompatActivity() {

    lateinit var pointsView: TextView
    lateinit var cardImage : ImageView

    val points : Int = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        pointsView = findViewById(R.id.pointTextView)
        var name = ""
        name = intent.getStringExtra("player").toString()
        Log.d("!!!", name)
        pointsView.text = "$name: $points poäng"

        val higherButton = findViewById<Button>(R.id.higherButton)
        val lowerButton = findViewById<Button>(R.id.lowerButton)
        val evenButton = findViewById<Button>(R.id.evenButton)

        higherButton.setOnClickListener {
            finish()
        }

    }
}