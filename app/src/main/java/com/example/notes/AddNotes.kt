package com.example.notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Note
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class AddNotes : AppCompatActivity() {
      private lateinit var  db:NotesDatabase
      lateinit var saveBtn:Button
      lateinit var title:EditText
      lateinit var content:EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_notes)
        title = findViewById(R.id.enterTitle)
        content = findViewById(R.id.enterContent)

        db = NotesDatabase(this)
        saveBtn = findViewById(R.id.saveBtn)

        saveBtn.setOnClickListener {
            val title = title.text.toString()
            val content = content.text.toString()
            val note = Data(0,title,content)
            db.insertData(note)
            finish()
            Toast.makeText(this,"Note Saved",Toast.LENGTH_SHORT).show()
        }
    }
}