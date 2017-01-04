package ua.nure.havrysh.kindergarten.ui.markscalendar;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.widget.FrameLayout;
import android.widget.TextView;

import ua.nure.havrysh.kindergarten.model.Mark;

/**
 * Created by Oleg on 17.12.2016.
 */

public class DayView extends FrameLayout {
    TextView text;
    IndicatorsView indicatorsView;

    @Nullable
    private Mark mark;

    public DayView(Context context) {
        super(context);

        text = new TextView(context);
        addView(text);

        indicatorsView = new IndicatorsView(context);


      /*FrameLayout.LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 80);
        addView(indicatorsView, layoutParams);*/

        setPadding(4, 4, 4, 4);
    }

    public void setState(@Nullable Mark mark, int day) {
        this.mark = mark;
        text.setText(String.valueOf(day));

        if (mark != null) {
            float avg = mark.getAvgMark();
            text.setTextColor(Color.rgb(255, (int) (255 * avg), (int) (255 * avg)));
            indicatorsView.setMark(mark);
        } else {
            //clear state
            text.setTextColor(Color.BLACK);
        }


    }


}
