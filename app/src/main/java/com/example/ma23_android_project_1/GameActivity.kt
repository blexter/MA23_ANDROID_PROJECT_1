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
import kotlin.system.exitProcess

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

        higherButton.setOnClickListener {
            checkGame("<")
        }

        lowerButton.setOnClickListener {
            checkGame(">")
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
        val newCard = getCard()

        if (newCard != null) {
            currentValue = currentCard.value as Int

            when (operator) {
                "=" -> if (oldValue == currentValue) points++
                "<" -> if (oldValue < currentValue) points++
                ">" -> if (oldValue > currentValue) points++
            }

            cardImage.setImageDrawable(currentCard.card)
            writePoints()
        }
        else {
            endActivity()
        }
    }
    private fun getCard(){
        val newCard = card.getCard(Random.nextInt(card.returnSize()))

        if (newCard != null) {
            currentCard = newCard
            currentValue = currentCard.value as Int
            cardImage.setImageDrawable(currentCard.card)
        }
        else {
            endActivity()
            return
        }
    }

    private fun endActivity(){
        finish()
    }
    class Deck(context : Context) {

            private val cards = mutableListOf(
                R.drawable.clubs2,
                R.drawable.diamonds3
            )

            private val values = mutableListOf(
                2,
                3
            )

        private val deck: MutableList<ImageItem> = mutableListOf()
        init {
            populateDeck(context)
        }
        private fun populateDeck(context: Context) {
            for (index in cards.indices) {
                val card = context.getDrawable(cards[index])!!
                val value = values.getOrNull(index) ?: 0
                deck.add(ImageItem(card, value))
            }
        }

        fun removeCard(index : Int){
            if(index in 0 until deck.size){
                deck.removeAt(index)
            }
        }

        fun getCard (index: Int): ImageItem? {
            if (returnSize() > 0) {
                val newCard = deck[index]
                removeCard(index)
                return newCard
            }
            else {
                return null
            }
        }

        fun returnSize() : Int {
            return deck.size
        }
    }
}