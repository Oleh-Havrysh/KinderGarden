package ua.nure.havrysh.kindergarten.activities

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.AdapterView
import android.widget.ListAdapter
import kindergarten.hakito.kindergartenclient.R
import kindergarten.hakito.kindergartenclient.databinding.ActivityGroupBinding
import kotlinx.android.synthetic.main.activity_group.listView
import kotlinx.android.synthetic.main.activity_group.text_teacher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ua.nure.havrysh.kindergarten.App
import ua.nure.havrysh.kindergarten.adapters.ChildrenAdapter
import ua.nure.havrysh.kindergarten.model.Child
import ua.nure.havrysh.kindergarten.model.Group
import ua.nure.havrysh.kindergarten.rest.Rest

class GroupActivity : AppCompatActivity() {
    
    lateinit var adapter: ListAdapter
    lateinit var binding: ActivityGroupBinding
    lateinit var group: Group
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_group)
        
        val groupId = intent.getStringExtra(EXTRA_GROUP_ID)
        loadGroup(groupId)
        
        listView.onItemClickListener =
                AdapterView.OnItemClickListener { _, _, position, _ ->
                    val intent = Intent(this@GroupActivity, ChildActivity::class.java)
                    intent.putExtra(
                        ChildActivity.ARG_CHILD_ID,
                        (adapter.getItem(position) as Child).id
                    )
                    startActivity(intent)
                }
        
        text_teacher.setOnClickListener {
            val intent = Intent(this@GroupActivity, HumanActivity::class.java)
            intent.putExtra(HumanActivity.EXTRA_HUMAN_ID, group.teacher.id)
            startActivity(intent)
        }
    }
    
    private fun loadGroup(id: String) {
        App.scope.launch(Dispatchers.IO) {
            val rest = Rest.get()
            group = rest.getGroup(id).await()
            val children = rest.getChildren(group.id).await()
            
            withContext(Dispatchers.Main) {
                val teacher = group.teacher
                
                text_teacher.text = "${teacher.name} ${teacher.surname}"
                binding.group = group
                
                adapter = ChildrenAdapter(this@GroupActivity, children)
                listView.adapter = adapter
            }
        }
    }
    
    companion object {
        
        val EXTRA_GROUP_ID = "group_id"
    }
}
