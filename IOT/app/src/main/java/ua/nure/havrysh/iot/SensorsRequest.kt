package ua.nure.havrysh.iot

data class SensorsRequest(var activity: Int, var child: Child)

data class Child(var id: String)