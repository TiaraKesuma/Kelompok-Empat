package com.example.trollo.model

import androidx.room.ColumnInfo
import java.sql.Time
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "notes_table")
data class Task(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    @ColumnInfo(name = "title") var title: String?,
    @ColumnInfo(name = "description") var desc: String?,
    @ColumnInfo(name = "due") var due_date: String?
)
