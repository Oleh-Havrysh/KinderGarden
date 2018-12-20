package ua.nure.havrysh.kindergarten.activities

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kindergarten.hakito.kindergartenclient.R
import kindergarten.hakito.kindergartenclient.databinding.ActivityChildBinding
import kotlinx.android.synthetic.main.activity_child.buttonParent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ua.nure.havrysh.kindergarten.App
import ua.nure.havrysh.kindergarten.model.Child
import ua.nure.havrysh.kindergarten.rest.Rest
import ua.nure.havrysh.kindergarten.ui.markscalendar.MarksCalendarFragment

class ChildActivity : AppCompatActivity() {
    
    lateinit var child: Child
    lateinit var marksCalendarFragment: MarksCalendarFragment
    lateinit var binding: ActivityChildBinding
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_child)
        
        val childId = intent.getStringExtra(ARG_CHILD_ID)
        loadChild(childId)
        
        buttonParent.setOnClickListener {
            val intent = Intent(this@ChildActivity, HumanActivity::class.java)
            intent.putExtra(HumanActivity.EXTRA_HUMAN_ID, child.parent.id)
            startActivity(intent)
        }
        
        marksCalendarFragment = MarksCalendarFragment.newInstance(childId)
        supportFragmentManager.beginTransaction().replace(R.id.frame, marksCalendarFragment)
            .commit()
    }
    
    private fun loadChild(id: String) {
        App.scope.launch(Dispatchers.IO) {
            child = Rest.get().getChild(id).await()
            withContext(Dispatchers.Main) { binding.child = child }
        }
    }
    
    companion object {
        
        val ARG_CHILD_ID = "child_id"
    }
}
