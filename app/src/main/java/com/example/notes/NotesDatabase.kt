package com.example.notes

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.ContactsContract.CommonDataKinds.Note

class NotesDatabase(context: Context):SQLiteOpenHelper(context, DATABASE_NAME,null, DATABASE_VERSION) {
    companion object{
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "notes.db"
        private const val TABLE_NAME= "allnotes"
        private const val COLUMN_ID= "id"
        private const val COLUMN_TITLE= "title"
        private const val COLUMN_CONTENT= "content"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createtable = "CREATE TABLE $TABLE_NAME ($COLUMN_ID INTEGER PRIMARY KEY,$COLUMN_TITLE TEXT,$COLUMN_CONTENT TEXT)"
        db?.execSQL(createtable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val dropTableQuery = "DROP TABLE IF EXISTS $TABLE_NAME"
        db?.execSQL(dropTableQuery)
        onCreate(db) // to recreate the table with the updated schema
    }

    fun insertData(note:Data){
        val db = writableDatabase
        val value = ContentValues().apply {
            put(COLUMN_TITLE,note.title)
            put(COLUMN_CONTENT,note.content)

        }
        db.insert(TABLE_NAME,null,value)
        db.close()

    }
    fun getAllNotes():List<Data>{
        val notesList = mutableListOf<Data>()
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_NAME"
        val cursor = db.rawQuery(query,null) // itirating through row

        while (cursor.moveToNext()){
            val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
            val title = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE))
            val content = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CONTENT))

            val note = Data(id,title,content)
            notesList.add(note)
        }
        cursor.close()
        db.close()
        return notesList
    }
    fun updateNote(note:Data){
            val db = writableDatabase
            val value = ContentValues().apply {
                put(COLUMN_TITLE,note.title)
                put(COLUMN_CONTENT,note.content)
            }
        val whereClause = "$COLUMN_ID = ?"
        val whereArgs = arrayOf(note.id.toString())
        db.update(TABLE_NAME,value,whereClause,whereArgs)
        db.close()
    }
    fun getNotesById(noteId:Int):Data{
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_NAME WHERE $COLUMN_ID = $noteId"
        val cursor = db.rawQuery(query,null)
        cursor.moveToFirst()


        val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
        val title = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE))
        val content = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CONTENT))

        cursor.close()
        db.close()
        return Data(id,title,content)

    }


}
