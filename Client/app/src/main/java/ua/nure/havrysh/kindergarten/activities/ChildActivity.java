package ua.nure.havrysh.kindergarten.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import kindergarten.hakito.kindergartenclient.R;
import kindergarten.hakito.kindergartenclient.databinding.ActivityChildBinding;
import retrofit2.Call;
import retrofit2.Response;
import ua.nure.havrysh.kindergarten.model.Child;
import ua.nure.havrysh.kindergarten.rest.MyCallback;
import ua.nure.havrysh.kindergarten.rest.Rest;
import ua.nure.havrysh.kindergarten.ui.markscalendar.MarksCalendarFragment;

public class ChildActivity extends AppCompatActivity {

    public static final String ARG_CHILD_ID = "child_id";

    Child child;
    MarksCalendarFragment marksCalendarFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActivityChildBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_child);
        long childId = getIntent().getLongExtra(ARG_CHILD_ID, 0);
        Rest.get().getChild(childId).enqueue(new MyCallback<Child>() {
            @Override
            public void onResponse(Call<Child> call, Response<Child> response) {
                child = response.body();
                binding.setChild(child);
            }

            @Override
            public void onFailure(Call<Child> call, Throwable t) {

            }
        });

        findViewById(R.id.buttonParent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChildActivity.this, HumanActivity.class);
                intent.putExtra(HumanActivity.EXTRA_HUMAN_ID, child.getParent_id());
                startActivity(intent);
            }
        });

        marksCalendarFragment = MarksCalendarFragment.newInstance(childId);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame, marksCalendarFragment).commit();
    }
}
