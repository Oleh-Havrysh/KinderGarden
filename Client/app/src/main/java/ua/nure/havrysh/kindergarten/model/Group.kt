package ua.nure.havrysh.kindergarten.model

data class Group(
    var id: String = "",
    var name: String? = null,
    var teacher: Human = Human()
) {
    
    fun getTeacherName() = "${teacher.name} ${teacher.surname}"
}
