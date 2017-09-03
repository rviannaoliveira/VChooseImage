package com.rviannaoliveira.vchooseimage

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

/**
 * Criado por rodrigo on 03/09/17.
 */
class BasicAdapter(private val list: ArrayList<String>) : RecyclerView.Adapter<BasicAdapter.BasicHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BasicHolder {
        return BasicHolder(LayoutInflater.from(parent?.context).inflate(R.layout.item, parent, false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: BasicHolder?, position: Int) {
        holder?.description?.text = list[position]
    }


    inner class BasicHolder(item: View) : RecyclerView.ViewHolder(item) {
        val description: TextView = itemView.findViewById<TextView>(R.id.description)
    }
}