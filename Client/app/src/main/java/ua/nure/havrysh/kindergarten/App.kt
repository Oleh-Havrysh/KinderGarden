package ua.nure.havrysh.kindergarten

import android.app.Application
import android.util.Log
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import ua.nure.havrysh.kindergarten.rest.AccessTokenStorage
import ua.nure.havrysh.kindergarten.rest.Rest
import ua.nure.havrysh.kindergarten.ui.toast
import kotlin.coroutines.CoroutineContext

class App : Application() {
    
    override fun onCreate() {
        super.onCreate()
        Rest.accessTokenStorage = AccessTokenStorage(applicationContext)
        
        scope = object : CoroutineScope {
            override val coroutineContext: CoroutineContext
                get() =
                    CoroutineExceptionHandler { coroutineContext, throwable ->
                        Log.e("App", "Unhandled error", throwable)
                        GlobalScope.launch(Dispatchers.Main) {
                            toast(throwable.message ?: "Some error :(")
                        }
                    }
        }
    }
    
    companion object {
        lateinit var scope: CoroutineScope
    }
}