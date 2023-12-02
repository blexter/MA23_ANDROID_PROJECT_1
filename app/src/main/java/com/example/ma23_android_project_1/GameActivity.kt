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
        //val name: String
        //name = intent.getStringExtra("player") //.toString()
        name = intent.getStringExtra("player")
        Log.d("!!!", "$name")
        writePoints()

        val higherButton = findViewById<Button>(R.id.higherButton)
        val lowerButton = findViewById<Button>(R.id.lowerButton)
        val evenButton = findViewById<Button>(R.id.evenButton)


        getCard()
        currentValue = currentCard.value as Int
        cardImage.setImageDrawable(currentCard.card)

        higherButton.setOnClickListener {
            val oldValue = currentValue
            getCard()
            val compResult : Int = oldValue.toString().compareTo(currentValue.toString())
            if(compResult > 0)
                points++

            cardImage.setImageDrawable(currentCard.card)
        }
        lowerButton.setOnClickListener {

            val oldValue = currentValue
            getCard()
            val compResult : Int = oldValue.toString().compareTo(currentValue.toString())
            if(compResult < 0)
                points++

            cardImage.setImageDrawable(currentCard.card)

        }

        evenButton.setOnClickListener {
            val oldValue = currentValue
            getCard()
            val newValue = currentValue
            //val compResult: Int = (oldValue as Comparable<*>).compareTo(currentValue as Comparable<*>)

            if(oldValue == newValue)
                points++

            cardImage.setImageDrawable(currentCard.card)
            writePoints()
        }



    }
    fun writePoints() {
        pointsView.text = " $name : $points poäng"
    }

    private fun getCard(){
        currentCard = card.getCard(Random.nextInt(card.returnSize()))
        currentValue = currentCard.value as Int

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