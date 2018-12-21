package ua.nure.havrysh.kindergarten.activities

import android.content.DialogInterface
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.DatePicker
import kindergarten.hakito.kindergartenclient.R
import kindergarten.hakito.kindergartenclient.databinding.ActivityAnnouncementBinding
import kotlinx.android.synthetic.main.activity_announcement.textView7
import kotlinx.android.synthetic.main.activity_announcement.text_title
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ua.nure.havrysh.kindergarten.App
import ua.nure.havrysh.kindergarten.model.Announcement
import ua.nure.havrysh.kindergarten.rest.AccessTokenStorage
import ua.nure.havrysh.kindergarten.rest.Rest
import java.sql.Date
import java.util.Calendar

class AnnouncementActivity : BaseEditingActivity() {
    lateinit var announcement: Announcement
    lateinit var binding: ActivityAnnouncementBinding
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        binding = DataBindingUtil.setContentView(this, R.layout.activity_announcement)
        
        if (intent.hasExtra(EXTRA_ANN)) {
            loadAnnouncement(intent.getStringExtra(EXTRA_ANN))
        } else {
            announcement = Announcement()
            binding.ann = announcement
        }
        
        findViewById<View>(R.id.text_date).setOnClickListener {
            val dialog = AlertDialog.Builder(this@AnnouncementActivity)
                .setView(R.layout.calendar)
                .create()
            
            dialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK") { di, which ->
                val calendarView = dialog.findViewById<View>(R.id.calendarView) as DatePicker?
                val calendar = Calendar.getInstance()
                calendar.timeInMillis = announcement.expires!!.time
                calendar.set(Calendar.YEAR, calendarView!!.year)
                calendar.set(Calendar.MONTH, calendarView.month)
                calendar.set(Calendar.DAY_OF_MONTH, calendarView.dayOfMonth)
                
                announcement.expires = Date(calendar.timeInMillis)
                binding.notifyChange()
            }
            
            dialog.show()
        }
    
        if (!isEditableActivity) {
            text_title.isEnabled = false
            textView7.isEnabled = false
        }
    }
    
    override fun isEditableActivity(): Boolean {
        return AccessTokenStorage.role != AccessTokenStorage.Role.PARENT
    }
    
    override fun onSave() {
        saveAnnouncement()
    }
    
    private fun loadAnnouncement(id: String) {
        App.scope.launch(Dispatchers.IO) {
            announcement = Rest.get().getAnnouncement(id).await()
            withContext(Dispatchers.Main) {
                binding.ann = announcement
            }
        }
    }
    
    private fun saveAnnouncement() {
        App.scope.launch(Dispatchers.IO) {
            Rest.get().saveAnnouncement(announcement).await()
            withContext(Dispatchers.Main) {
                finish()
            }
        }
    }
    
    companion object {
        val EXTRA_ANN = "ann"
    }
}
