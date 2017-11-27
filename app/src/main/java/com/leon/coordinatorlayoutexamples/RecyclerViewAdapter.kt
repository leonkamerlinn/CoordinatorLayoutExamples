package com.leon.coordinatorlayoutexamples

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import org.jetbrains.anko.find

/**
 * Created by Leon on 29.10.2017..
 */
class RecyclerViewAdapter(val context: Context) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        with(holder!!) {
            textView.text = "Item $position"

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_list, parent, false)

        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int = 100


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView = itemView.find<TextView>(R.id.textView)
    }
}