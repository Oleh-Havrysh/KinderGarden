package ua.nure.havrysh.kindergarten.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ListAdapter
import kindergarten.hakito.kindergartenclient.R
import kotlinx.android.synthetic.main.fragment_groups.listView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ua.nure.havrysh.kindergarten.App
import ua.nure.havrysh.kindergarten.activities.GroupActivity
import ua.nure.havrysh.kindergarten.adapters.GroupsAdapter
import ua.nure.havrysh.kindergarten.model.Group
import ua.nure.havrysh.kindergarten.rest.Rest

class GroupsFragment : android.support.v4.app.Fragment() {
    
    lateinit var adapter: ListAdapter
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        
        return inflater.inflate(R.layout.fragment_groups, container, false)
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        listView.onItemClickListener =
                AdapterView.OnItemClickListener { _, _, position, _ ->
                    val intent = Intent(activity, GroupActivity::class.java)
                    intent.putExtra(
                        GroupActivity.EXTRA_GROUP_ID,
                        (adapter.getItem(position) as Group).id
                    )
                    startActivity(intent)
                }
        
        loadGroups()
    }
    
    private fun loadGroups() {
        App.scope.launch(Dispatchers.Main) {
            val groups = withContext(Dispatchers.IO) {
                Rest.get().getGroups().await()
            }
            
            adapter = GroupsAdapter(context, groups)
            listView.adapter = adapter
        }
    }
}
