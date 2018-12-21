package ua.nure.havrysh.kindergarten.model

import ua.nure.havrysh.kindergarten.rest.AccessTokenStorage

data class Human(
    var id: String = "",
    var name: String? = null,
    var surname: String? = null,
    var address: String? = null,
    var phone: String? = null,
    var email: String? = null,
    var role: Int = 0,
    var login: String? = null,
    var password: String? = null
) {
    
    fun getFullName() = "$name $surname"
    
    fun getFullNameWithRole() = "${AccessTokenStorage.mapRole(role).name} ${getFullName()}"
}