package ua.nure.havrysh.kindergarten.activities;

import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.DatePicker;

import java.util.Calendar;

import kindergarten.hakito.kindergartenclient.R;
import kindergarten.hakito.kindergartenclient.databinding.ActivityAnnouncementBinding;
import retrofit2.Call;
import retrofit2.Response;
import ua.nure.havrysh.kindergarten.model.Announcement;
import ua.nure.havrysh.kindergarten.rest.MyCallback;
import ua.nure.havrysh.kindergarten.rest.Rest;

public class AnnouncementActivity extends BaseEditingActivity {

    public static final String EXTRA_ANN = "ann";
    Announcement announcement;
    ActivityAnnouncementBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_announcement);

        if (getIntent().hasExtra(EXTRA_ANN)) {
            Rest.get().getAnnouncement(getIntent().getLongExtra(EXTRA_ANN, 0)).enqueue(new MyCallback<Announcement>() {
                @Override
                public void onResponse(Call<Announcement> call, Response<Announcement> response) {
                    announcement = response.body();
                    binding.setAnn(announcement);
                }

                @Override
                public void onFailure(Call<Announcement> call, Throwable t) {

                }
            });
        } else {
            announcement = new Announcement();
            binding.setAnn(announcement);
        }



        findViewById(R.id.text_date).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog dialog = new AlertDialog.Builder(AnnouncementActivity.this).setView(R.layout.calendar).create();
                dialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface di, int which) {
                        DatePicker calendarView = (DatePicker) dialog.findViewById(R.id.calendarView);
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTimeInMillis(announcement.getExpires().getTime());
                        calendar.set(Calendar.YEAR, calendarView.getYear());
                        calendar.set(Calendar.MONTH, calendarView.getMonth());
                        calendar.set(Calendar.DAY_OF_MONTH, calendarView.getDayOfMonth());
                        binding.notifyChange();

                    }
                });

                dialog.show();

            }
        });
    }
}
