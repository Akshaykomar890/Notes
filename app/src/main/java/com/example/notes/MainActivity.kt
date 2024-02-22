package com.example.notes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RelativeLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    lateinit var button: FloatingActionButton
    private lateinit var db:NotesDatabase
    lateinit var recyclerView: RecyclerView

    lateinit var myAdapter: MyAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button = findViewById(R.id.addBtn)
        recyclerView = findViewById(R.id.recyclerview)

        db  = NotesDatabase(this)
        myAdapter = MyAdapter(db.getAllNotes(),this)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = myAdapter

        button.setOnClickListener {
            val intent = Intent(this,AddNotes::class.java)
            startActivity(intent)
        }


    }

    override fun onResume() {
        super.onResume()
        myAdapter.refereshingData(db.getAllNotes())
    }
}