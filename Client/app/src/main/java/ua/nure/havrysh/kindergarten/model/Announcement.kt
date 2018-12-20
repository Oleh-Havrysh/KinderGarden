package ua.nure.havrysh.kindergarten.model

import java.sql.Date

data class Announcement(
    var id: String = "",
    var title: String = "",
    var content: String = "",
    var creation_date: Date = Date(System.currentTimeMillis()),
    var expires: Date? = Date(System.currentTimeMillis())
)
