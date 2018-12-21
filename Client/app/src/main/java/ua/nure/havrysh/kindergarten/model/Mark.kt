package ua.nure.havrysh.kindergarten.model

import java.sql.Date
import java.text.SimpleDateFormat

data class Mark(
    var id: String = "",
    var date: Date? = null,
    var comment: String? = null,
    var behaviour: Long = 0,
    var sleeping: Long = 0,
    var eating: Long = 0,
    var child_id: Long = 0
) {
    
    val prettyDate: String
        get() = sdf.format(date)
    
    /**
     * @return average mark in range 0..1
     */
    val avgMark: Float
        get() = (behaviour.toFloat() + sleeping.toFloat() + eating.toFloat()) / 5f / 3f
    
    companion object {
        private val sdf = SimpleDateFormat()
    }
}
