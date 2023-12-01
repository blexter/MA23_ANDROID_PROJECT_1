package com.example.ma23_android_project_1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    lateinit var nameText : EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nameText = findViewById(R.id.nameEditText)

        val button = findViewById<Button>(R.id.continueButton)
        button.setOnClickListener {
            letsContinue()
        }

    }
    fun letsContinue(){
        val intent = Intent(this, GameActivity::class.java)
        var name : String
        name = nameText.text.toString()
        if(name.equals("")){
            name = "Player 1"
        }
        Log.d("!!!", nameText.text.toString())
        Log.d("!!!", name.toString())
        intent.putExtra("player", name)
        startActivity(intent)
    }
}