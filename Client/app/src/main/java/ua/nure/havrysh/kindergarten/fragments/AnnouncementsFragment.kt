package ua.nure.havrysh.kindergarten.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import kindergarten.hakito.kindergartenclient.R
import kotlinx.android.synthetic.main.fragment_announcements.fab_add
import kotlinx.android.synthetic.main.fragment_announcements.list_view

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ua.nure.havrysh.kindergarten.App
import ua.nure.havrysh.kindergarten.activities.AnnouncementActivity
import ua.nure.havrysh.kindergarten.adapters.AnnouncementsAdapter
import ua.nure.havrysh.kindergarten.model.Announcement
import ua.nure.havrysh.kindergarten.rest.AccessTokenStorage
import ua.nure.havrysh.kindergarten.rest.Rest

class AnnouncementsFragment : Fragment() {
    
    override fun onResume() {
        super.onResume()
        loadAnnouncements()
    }
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_announcements, container, false)
    }
    
    @SuppressLint("RestrictedApi")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (AccessTokenStorage.role == AccessTokenStorage.Role.PARENT) {
            fab_add.visibility = View.GONE
        }
        
        loadAnnouncements()
        
        fab_add.setOnClickListener { v ->
            val intent = Intent(v.context, AnnouncementActivity::class.java)
            startActivity(intent)
        }
        
        list_view.onItemClickListener =
                AdapterView.OnItemClickListener { parent, view, position, id ->
                    val intent = Intent(context, AnnouncementActivity::class.java)
                    intent
                        .putExtra(
                            AnnouncementActivity.EXTRA_ANN,
                            (list_view.adapter.getItem(position) as Announcement).id
                        )
                    startActivity(intent)
                }
    }
    
    fun loadAnnouncements() {
        App.scope.launch(Dispatchers.IO) {
            val list = Rest.get().getAnnouncements().await()
            withContext(Dispatchers.Main) {
                list_view.adapter = AnnouncementsAdapter(context, list)
            }
        }
    }
}
