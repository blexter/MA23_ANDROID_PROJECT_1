package com.example.ma23_android_project_1

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class StatisticsRecyclerAdapter(val context: Context, val highscores: List<Pair<String, Double>>) : RecyclerView.Adapter<StatisticsRecyclerAdapter.ViewHolder>() {

    val layoutInflater = LayoutInflater.from(context)

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView = itemView.findViewById<TextView>(R.id.nameTextView)
        val pointTextView = itemView.findViewById<TextView>(R.id.pointTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = layoutInflater.inflate(R.layout.list_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount() = minOf(highscores.count(), 5)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val highscore = highscores.getOrNull(position)
        if (highscore != null) {
            holder.nameTextView.text = highscore.first
            holder.pointTextView.text = highscore.second.toInt().toString()
        }
    }

}