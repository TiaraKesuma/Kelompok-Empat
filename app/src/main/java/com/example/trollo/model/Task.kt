package com.example.trollo.model

import java.sql.Time

data class Task(
    var title: String,
    var desc: String,
    var due_date: Time
)
