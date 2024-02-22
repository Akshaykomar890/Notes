package com.example.notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts

class UpdateActivity : AppCompatActivity() {
    lateinit var updateButton:Button
    private lateinit var db: NotesDatabase
    lateinit var title:EditText
    lateinit var content:EditText
    private var noteId:Int = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)

        title = findViewById(R.id.enterTitleUpdate)
        content= findViewById(R.id.enterContentUpdate)
        updateButton = findViewById(R.id.saveBtnUpdate)
        db = NotesDatabase(this)
        noteId = intent.getIntExtra("note_id",-1)
        if(noteId == -1){
            finish()
            return
        }
        val note = db.getNotesById(noteId)
        title.setText(note.title)
        content.setText(note.content)


        updateButton.setOnClickListener {
            val newTitle = title.text.toString()
            val newContent = content.text.toString()
            val updateNote = Data(noteId,newTitle,newContent)
            db.updateNote(updateNote)
            finish()
            Toast.makeText(this@UpdateActivity,"Updated",Toast.LENGTH_SHORT).show()

        }


    }
}