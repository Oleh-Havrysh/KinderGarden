package ua.nure.havrysh.kindergarten.model

import java.sql.Date

data class Child(
    var id: String = "",
    var name: String? = null,
    var surname: String? = null,
    var birth_date: Date? = null,
    var group_id: Long = 0,
    var parent: Human = Human(),
    var notice: String? = null
) {
    
    fun getFullName() = "$name $surname"
    
    val icon: String
        get() = "child_$id"
}