package com.example.trollo.database

import android.provider.ContactsContract.CommonDataKinds.Note
import androidx.lifecycle.LiveData
import com.example.trollo.model.Task

class NotesRepository(private val noteDao : NoteDAO) {

    val allNotes : LiveData<List<Task>> = noteDao.getAllNotes()

    suspend fun insert(note : Task){

        noteDao.insert(note)

    }

    suspend fun delete(note : Task){

        noteDao.delete(note)

    }

    suspend fun update(note : Task){

        noteDao.update(note.id, note.title, note.desc, note.due_date)

    }
}