package com.example.ma23_android_project_1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {

    lateinit var nameText: EditText
    lateinit var highscoreText: TextView
    private lateinit var startForResult: ActivityResultLauncher<Intent>

    companion object {
        private const val REQUEST_GAME = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nameText = findViewById(R.id.nameEditText)
        highscoreText = findViewById(R.id.highscoreTextView)

        val button = findViewById<Button>(R.id.continueButton)

        startForResult =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == RESULT_OK) {

                    val data = result.data
                    val highScoreJson = data?.getStringExtra("highscore")

                    if (highScoreJson != null) {
                        var highScoreList: List<Pair<String, Int>> = Gson().fromJson(highScoreJson, object : TypeToken<List<Pair<String, Int>>>() {}.type)

                        highScoreList = highScoreList.sortedByDescending { it.second.toInt() }
                        var highscore = "Highscore: \n"

                        for ((name, point) in highScoreList.subList(0, minOf(5, highScoreList.size))) {
                            highscore += "$name: $point poäng \n"
                        }

                        highscoreText.text = highscore
                    }
                }
            }

        button.setOnClickListener {
            letsContinue()
        }
    }

    fun letsContinue() {
        val intent = Intent(this, GameActivity::class.java)
        val name: String = nameText.text.toString().takeIf { it.isNotBlank() } ?: "Player 1"

        intent.putExtra("player", name)
        startForResult.launch(intent)
    }
}