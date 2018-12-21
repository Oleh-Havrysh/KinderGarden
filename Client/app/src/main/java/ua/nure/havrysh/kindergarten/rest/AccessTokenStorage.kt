package ua.nure.havrysh.kindergarten.rest

import android.content.Context
import android.preference.PreferenceManager

class AccessTokenStorage(private val context: Context) {
    
    fun getToken() = PreferenceManager.getDefaultSharedPreferences(context)
        .getString(ACCESS_TOKEN, "")
    
    fun setToken(accessToken: String) = PreferenceManager.getDefaultSharedPreferences(context)
        .edit()
        .putString(ACCESS_TOKEN, accessToken)
        .apply()
    
    fun setRole(role: Int) = PreferenceManager.getDefaultSharedPreferences(context)
        .edit()
        .putInt(ROLE, role)
        .apply()
    
    fun getRole() = PreferenceManager.getDefaultSharedPreferences(context)
        .getInt(ROLE, 0)
    
    fun loadRole() {
        role = mapRole(getRole())
    }
    
    enum class Role {
        TEACHER, PARENT, ADMIN
    }
    
    companion object {
        var role: Role = Role.PARENT
    
        fun mapRole(role: Int): Role = when (role) {
            0 -> Role.PARENT
            1 -> Role.TEACHER
            2 -> Role.ADMIN
            else -> Role.PARENT
        }
    
        private const
        val ACCESS_TOKEN = "ACCESS_TOKEN"
        private const val ROLE = "ROLE"
    }
}