package com.example.notes

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class MyAdapter(private var notes:List<Data>, context: Context):
        RecyclerView.Adapter<MyAdapter.ViewHolder>(){
            class ViewHolder(itemview:View):RecyclerView.ViewHolder(itemview){
                val titleTextView:TextView = itemview.findViewById(R.id.titleTextView)
                val contentTextView:TextView = itemview.findViewById(R.id.contentTextView)
                val updateButton:ImageView = itemview.findViewById(R.id.updatebtn)

            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewholder = LayoutInflater.from(parent.context).inflate(R.layout.each_item,parent,false)
        return ViewHolder(viewholder)

    }

    override fun getItemCount(): Int {
        return notes.size

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val note = notes[position]
        holder.titleTextView.text = note.title
        holder.contentTextView.text = note.content
        holder.updateButton.setOnClickListener {
            val intent = Intent(holder.itemView.context,UpdateActivity::class.java).apply {
                putExtra("note_id",note.id)
            }
            holder.itemView.context.startActivity(intent)
        }



    }

    fun refereshingData(newnotes:List<Data>){
        notes = newnotes
        notifyDataSetChanged()
    }


}