package ua.nure.havrysh.kindergarten.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View.GONE
import android.view.View.VISIBLE
import kindergarten.hakito.kindergartenclient.R
import kotlinx.android.synthetic.main.activity_login.loginButton
import kotlinx.android.synthetic.main.activity_login.loginEditText
import kotlinx.android.synthetic.main.activity_login.loginProgressBar
import kotlinx.android.synthetic.main.activity_login.passwordEditText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ua.nure.havrysh.kindergarten.App
import ua.nure.havrysh.kindergarten.model.LoginRequest
import ua.nure.havrysh.kindergarten.rest.AccessTokenStorage
import ua.nure.havrysh.kindergarten.rest.Rest

class LoginActivity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        
        loginButton.setOnClickListener {
            login(loginEditText.text.toString(), passwordEditText.text.toString())
        }
    }
    
    private fun login(login: String, password: String) {
        App.scope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                loginProgressBar.visibility = VISIBLE
            }
            
            val loginResult =
                try {
                    Rest.get().login(LoginRequest(login, password)).await()
                } finally {
                    withContext(Dispatchers.Main) {
                        loginProgressBar.visibility = GONE
                    }
                }
    
            val tokenStorage = AccessTokenStorage(this@LoginActivity)
            tokenStorage.setToken(loginResult.accessToken)
            
            val self = Rest.get().getSelf().await()
            tokenStorage.setRole(self.role)
            
            AccessTokenStorage(this@LoginActivity).loadRole()
    
            onSuccessLogin(loginResult.accessToken)
        }
    }
    
    private fun onSuccessLogin(accessToken: String) {
        startActivity(Intent(this, MainActivity::class.java))
    }
}

