package ua.nure.havrysh.kindergarten.ui.markscalendar;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kindergarden.hakito.kindergardenclient.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ua.nure.havrysh.kindergarten.model.Mark;
import ua.nure.havrysh.kindergarten.rest.MyCallback;
import ua.nure.havrysh.kindergarten.rest.Rest;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MarksCalendarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MarksCalendarFragment extends Fragment {
    private static final String ARG_CHILD_ID = "child_id";


    public MarksCalendarFragment() {
        // Required empty public constructor
    }


    public static MarksCalendarFragment newInstance(long childId) {
        MarksCalendarFragment fragment = new MarksCalendarFragment();
        Bundle args = new Bundle();
        args.putLong(ARG_CHILD_ID, childId);
        fragment.setArguments(args);
        return fragment;
    }

    @BindView(R.id.marks_calendar)
    public MarksCalendar marksCalendar;

    @BindView(R.id.text_mounth)
    public TextView mounth;

    long childId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        childId = getArguments().getLong(ARG_CHILD_ID);
    }

    @OnClick(R.id.btn_prev)
    public void prevMounth() {
        marksCalendar.rollMounth(false);
        mounth.setText(marksCalendar.getCurrentMounth());
    }

    @OnClick(R.id.btn_next)
    public void nextMounth() {
        marksCalendar.rollMounth(true);
        mounth.setText(marksCalendar.getCurrentMounth());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_marks_calendar, container, false);
        ButterKnife.bind(this, v);

        mounth.setText(marksCalendar.getCurrentMounth());

        Rest.get().getMarks(childId).enqueue(new MyCallback<List<Mark>>() {
                @Override
                public void onResponse(Call<List<Mark>> call, Response<List<Mark>> response) {
                    marksCalendar.setMarks(response.body());
                }

                @Override
            public void onFailure(Call<List<Mark>> call, Throwable t) {

            }
        });

        return v;
    }

}
