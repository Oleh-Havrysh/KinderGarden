package ua.nure.havrysh.kindergarten.ui.markscalendar

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kindergarten.hakito.kindergartenclient.R
import kotlinx.android.synthetic.main.fragment_marks_calendar.btn_next
import kotlinx.android.synthetic.main.fragment_marks_calendar.btn_prev
import kotlinx.android.synthetic.main.fragment_marks_calendar.marks_calendar
import kotlinx.android.synthetic.main.fragment_marks_calendar.text_mounth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ua.nure.havrysh.kindergarten.App
import ua.nure.havrysh.kindergarten.rest.Rest

class MarksCalendarFragment : Fragment() {
    
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_marks_calendar, container, false)
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        val childId = arguments!!.getString(ARG_CHILD_ID)
        loadMarks(childId)
        
        text_mounth.text = marks_calendar!!.currentMounth
        btn_next.setOnClickListener {
            marks_calendar.rollMounth(true)
            text_mounth.text = marks_calendar!!.currentMounth
        }
        btn_prev.setOnClickListener {
            marks_calendar.rollMounth(false)
            text_mounth.text = marks_calendar!!.currentMounth
            
        }
    }
    
    private fun loadMarks(childId: String) {
        App.scope.launch(Dispatchers.IO) {
            val marks = Rest.get().getMarks(childId).await()
            withContext(Dispatchers.Main) {
                marks_calendar.setMarks(marks)
            }
        }
    }
    
    companion object {
        private val ARG_CHILD_ID = "child_id"
        
        fun newInstance(childId: String): MarksCalendarFragment {
            val fragment = MarksCalendarFragment()
            val args = Bundle()
            args.putString(ARG_CHILD_ID, childId)
            fragment.arguments = args
            return fragment
        }
    }
}
