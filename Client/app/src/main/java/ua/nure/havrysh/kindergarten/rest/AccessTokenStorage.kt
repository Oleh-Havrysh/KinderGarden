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
    
    companion object {
        private const val ACCESS_TOKEN = "ACCESS_TOKEN"
    }
}