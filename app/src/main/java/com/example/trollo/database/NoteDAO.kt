package com.example.trollo.database

import android.icu.text.CaseMap.Title
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.trollo.model.Task

@Dao
interface NoteDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note : Task)

    @Delete
    suspend fun delete(note : Task)

    @Query("Select * from notes_table order by id ASC")
    fun getAllNotes() : LiveData<List<Task>>

    @Query("UPDATE notes_table Set title = :title, description = :desc, due = :due WHERE id = :id")
    suspend fun update(id : Int?, title : String?, desc : String?, due : String?)
}