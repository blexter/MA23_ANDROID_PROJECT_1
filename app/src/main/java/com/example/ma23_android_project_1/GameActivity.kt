package com.example.ma23_android_project_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.content.Context
import android.graphics.drawable.Drawable
import kotlin.random.Random

data class ImageItem(val card: Drawable, val value: Comparable<*>)

class GameActivity : AppCompatActivity() {

    private lateinit var pointsView: TextView
    private lateinit var cardImage : ImageView


    private var name : String? = null
    private var points : Int = 0
    private var currentValue: Int = 0
    private lateinit var currentCard : ImageItem
    private val card: Deck by lazy {
        Deck(applicationContext)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        cardImage = findViewById(R.id.cardImageView)
        pointsView = findViewById(R.id.pointTextView)
        val higherButton = findViewById<Button>(R.id.higherButton)
        val lowerButton = findViewById<Button>(R.id.lowerButton)
        val evenButton = findViewById<Button>(R.id.evenButton)

        name = intent.getStringExtra("player")

        writePoints()
        getCard()
        //currentValue = currentCard.value as Int
        //cardImage.setImageDrawable(currentCard.card)



        higherButton.setOnClickListener {
            checkGame(">")
        }
        lowerButton.setOnClickListener {

            checkGame("<")

        }

        evenButton.setOnClickListener {
            checkGame("=")
        }



    }
    fun writePoints() {
        pointsView.text = " $name : $points poäng"
    }

    fun checkGame(operator : String){

        val oldValue = currentValue
        getCard()
        val newValue = currentValue

        if(operator == "=") {
            if (oldValue == newValue)
                points++
        }
        if(operator == "<") {
            if (oldValue < newValue)
                points++
        }
        if(operator == ">") {
            if (oldValue > newValue)
                points++
        }
        //cardImage.setImageDrawable(currentCard.card)
        writePoints()
    }

    private fun getCard(){
        currentCard = card.getCard(Random.nextInt(card.returnSize()))
        currentValue = currentCard.value as Int
        cardImage.setImageDrawable(currentCard.card)

    }
    class Deck(context : Context) {
            private val cards = arrayOf(
                R.drawable.clubs2,
                R.drawable.diamonds3
            )
            private val values = arrayOf(
                2,
                3
            )
        private val deck: List<ImageItem> = cards.mapIndexed { index, resourceId ->
            val card = context.getDrawable(resourceId)!!
            val value = values.getOrNull(index) ?: 0
            ImageItem(card, value)
        }
        
        fun getCard (index: Int): ImageItem{
            return deck[index]
        }

        fun returnSize() : Int {
            return deck.size
        }
    }
}