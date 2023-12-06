package com.example.ma23_android_project_1

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Im
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class StatsticsActivity : AppCompatActivity() {

    private lateinit var  recyclerView : RecyclerView
    private var highScoreList: List<Pair<String, Double>> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statstics)

        val headerText = findViewById<TextView>(R.id.headerTextView)
        headerText.text = "Highscore"
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val highScoreJson = intent.getStringExtra("highscore")

        if (highScoreJson != null) {
            highScoreList = Gson().fromJson(highScoreJson, object : TypeToken<List<Pair<String, Double>>>() {}.type)
            highScoreList = highScoreList.sortedByDescending { it.second.toInt() }
            recyclerView.adapter = StatisticsRecyclerAdapter(this, highScoreList)
        }

        val returnButton = findViewById<Button>(R.id.backButton)
        val restartButton = findViewById<ImageButton>(R.id.restartButton)

        returnButton.text = "<-"

        returnButton.setOnClickListener {
            finish()
        }

        restartButton.setOnClickListener{
            restart()
        }
    }
    private fun restart(){
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
        startActivity(intent)
        finish()
    }
}