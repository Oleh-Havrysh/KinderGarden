package ua.nure.havrysh.kindergarten.activities

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kindergarten.hakito.kindergartenclient.R
import kindergarten.hakito.kindergartenclient.databinding.ActivityHumanBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ua.nure.havrysh.kindergarten.App
import ua.nure.havrysh.kindergarten.model.Human
import ua.nure.havrysh.kindergarten.rest.Rest

class HumanActivity : AppCompatActivity() {
    
    lateinit var human: Human
    lateinit var binding: ActivityHumanBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_human)
        val humanId = intent.getStringExtra(EXTRA_HUMAN_ID)
        
        if (humanId.isNotEmpty()) {
            loadHuman(humanId)
        } else {
            human = Human()
            binding.human = human
        }
    }
    
    private fun loadHuman(id: String) {
        App.scope.launch(Dispatchers.IO) {
            human = Rest.get().getHuman(id).await()
            withContext(Dispatchers.Main) {
                binding.human = human
            }
        }
    }
    
    companion object {
        
        val EXTRA_HUMAN_ID = "parentId"
    }
}
