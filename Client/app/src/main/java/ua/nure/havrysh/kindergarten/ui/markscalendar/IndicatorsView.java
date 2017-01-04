package ua.nure.havrysh.kindergarten.ui.markscalendar;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import ua.nure.havrysh.kindergarten.model.Mark;

/**
 * Created by Oleg on 19.12.2016.
 */

public class IndicatorsView extends View {

    private Mark mark;

    public IndicatorsView(Context context) {
        super(context);
    }


    Paint commentPaint = new Paint();

    {
        commentPaint.setColor(Color.GRAY);
        commentPaint.setStyle(Paint.Style.FILL);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        if (mark == null) {
            return;
        }
        if (mark.getComment() != null) {
            canvas.drawCircle(10, 10, 5, commentPaint);

        }

    }



    public void setMark(Mark mark) {
        this.mark = mark;
        invalidate();
    }
}
