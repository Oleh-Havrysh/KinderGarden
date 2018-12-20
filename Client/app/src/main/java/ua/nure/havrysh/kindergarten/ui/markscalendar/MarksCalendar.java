package ua.nure.havrysh.kindergarten.ui.markscalendar;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import ua.nure.havrysh.kindergarten.activities.MarkActivity;
import ua.nure.havrysh.kindergarten.model.Mark;

import static java.util.Calendar.YEAR;

/**
 * Created by Oleg on 17.12.2016.
 */

public class MarksCalendar extends TableLayout {

    private static final int WEEKS = 6, DAYS_IN_WEEK = 7;

    private static final String TAG = MarksCalendar.class.getName();

    private Context context;
    private List<Mark> marks;
    private DayView[][] days;
    private Calendar currentMounth;

    public MarksCalendar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.context = context;
        days = new DayView[WEEKS][DAYS_IN_WEEK];

        createEmptyCalendar();

        currentMounth = Calendar.getInstance();
        currentMounth.set(Calendar.DAY_OF_MONTH, 1);
    }


    private void createEmptyCalendar() {
        Calendar calendar = Calendar.getInstance();

        int firstDayOfWeek = calendar.getMinimum(Calendar.DAY_OF_WEEK);

        calendar.set(Calendar.DAY_OF_WEEK, firstDayOfWeek);
        //fill day of week headers

        TableLayout.LayoutParams tableLayoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        TableRow.LayoutParams rowLayoutParams = new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        rowLayoutParams.weight = 1;
        TableRow headers = new TableRow(context);

        for (int i = 1; i <= DAYS_IN_WEEK; i++) {
            TextView h = new TextView(context);
            h.setText(calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.getDefault()));
            headers.addView(h, rowLayoutParams);
            calendar.roll(Calendar.DAY_OF_WEEK, true);
        }
        addView(headers, tableLayoutParams);


        for (int week = 1; week <= WEEKS; week++) {
            TableRow tr = new TableRow(context);
            for (int day = 1; day <= DAYS_IN_WEEK; day++) {

                DayView dayView = new DayView(context);
                days[week - 1][day - 1] = dayView;
                tr.addView(dayView, rowLayoutParams);
            }
            addView(tr, tableLayoutParams);
        }
    }

    public void setMarks(List<Mark> marks) {
        this.marks = marks;

        fillCalendar();
    }

    public void rollMounth(boolean up) {
        currentMounth.add(Calendar.MONTH, up ? 1 : -1);
        // currentMounth.roll(Calendar.MONTH, up);
        fillCalendar();
    }

    public String getCurrentMounth() {
        return currentMounth.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()) + " " + currentMounth.get(YEAR);
    }

    private Mark getMark(Calendar day) {
        if (marks == null) {
            Log.e(TAG, "Marks list is empty");
            return null;
        }
        Calendar c = Calendar.getInstance();
        for (Mark m : marks) {
            if (m.getDate() == null) {
                continue;
            }
            c.setTime(m.getDate());
            if (c.get(YEAR) == day.get(YEAR) &&
                    c.get(Calendar.DAY_OF_YEAR) == day.get(Calendar.DAY_OF_YEAR)) {
                return m;
            }

        }
        return null;
    }


    private void fillCalendar() {


        Calendar calendar = Calendar.getInstance();
        int firstDayOfWeek = calendar.getMinimum(Calendar.DAY_OF_WEEK);
        calendar.setTime(currentMounth.getTime());

        Log.d(TAG, new SimpleDateFormat().format(calendar.getTime()));
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Log.d(TAG, new SimpleDateFormat().format(calendar.getTime()));

        calendar.set(Calendar.DAY_OF_WEEK, firstDayOfWeek);
        Log.d(TAG, new SimpleDateFormat().format(calendar.getTime()));


        for (int week = 1; week <= WEEKS; week++) {
            for (int day = 1; day <= DAYS_IN_WEEK; day++) {
                DayView dayView = days[week - 1][day - 1];
                final Mark m = getMark(calendar);
                dayView.setState(m, calendar.get(Calendar.DAY_OF_MONTH));
                final long date = calendar.getTimeInMillis();
                dayView.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (m == null) {
                            MarkActivity.Companion.startForCreating(context, date);
                        } else {
                            MarkActivity.Companion.start(context, m.getId());
                        }

                    }
                });
                calendar.roll(Calendar.DAY_OF_YEAR, true);
            }
        }
    }
}
