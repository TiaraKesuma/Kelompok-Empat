package com.example.trollo.model.db

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.time.LocalDate

@Entity(tableName = "tasks")
@Parcelize()
data class Task(@PrimaryKey(autoGenerate = true) val id: Long?,
                      @ColumnInfo(name = "title") val title: String,
                      @ColumnInfo(name = "description") val description: String,
                      @ColumnInfo(name = "due_date") val due_date: String) : Parcelable