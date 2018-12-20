package ua.nure.havrysh.kindergarten.activities

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import kindergarten.hakito.kindergartenclient.R
import kindergarten.hakito.kindergartenclient.databinding.ActivityMarkBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ua.nure.havrysh.kindergarten.App
import ua.nure.havrysh.kindergarten.model.Mark
import ua.nure.havrysh.kindergarten.rest.Rest
import java.sql.Date

class MarkActivity : BaseEditingActivity() {
    
    lateinit var binding: ActivityMarkBinding
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_mark)
        
        val markId = intent.getStringExtra(EXTRA_MARK_ID)
        if (markId?.isNotEmpty() ?: false) {
            loadMark(markId)
        } else {
            var date = intent.getLongExtra(EXTRA_DATE, 0)
            val mark = Mark(date = Date(date))
            
            binding.mark = mark
        }
    }
    
    private fun loadMark(markId: String) {
        App.scope.launch(Dispatchers.IO) {
            val mark = Rest.get().getMark(markId).await()
            withContext(Dispatchers.Main) { binding.mark = mark }
        }
    }
    
    companion object {
        
        private val EXTRA_MARK_ID = "mark_id"
        private val EXTRA_DATE = "date"
        
        fun startForCreating(context: Context, date: Long) {
            val intent = Intent(context, MarkActivity::class.java)
            intent.putExtra(EXTRA_DATE, date)
            context.startActivity(intent)
        }
        
        fun start(context: Context, markId: String) {
            val intent = Intent(context, MarkActivity::class.java)
            intent.putExtra(EXTRA_MARK_ID, markId)
            context.startActivity(intent)
        }
    }
}
