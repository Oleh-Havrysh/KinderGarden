package ua.nure.havrysh.kindergarten.activities;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import java.sql.Date;

import kindergarten.hakito.kindergartenclient.R;
import kindergarten.hakito.kindergartenclient.databinding.ActivityMarkBinding;
import retrofit2.Call;
import retrofit2.Response;
import ua.nure.havrysh.kindergarten.model.Mark;
import ua.nure.havrysh.kindergarten.rest.MyCallback;
import ua.nure.havrysh.kindergarten.rest.Rest;

public class MarkActivity extends BaseEditingActivity {

    private static final String EXTRA_MARK_ID = "mark_id", EXTRA_DATE = "date";

    public static void startForCreating(Context context, long date) {
        Intent intent = new Intent(context, MarkActivity.class);
        intent.putExtra(EXTRA_DATE, date);
        context.startActivity(intent);
    }

    public static void start(Context context, long markId) {
        Intent intent = new Intent(context, MarkActivity.class);
        intent.putExtra(EXTRA_MARK_ID, markId);
        context.startActivity(intent);

    }

    ActivityMarkBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_mark);

        long markId = getIntent().getLongExtra(EXTRA_MARK_ID, 0);
        if (markId > 0) {
            Rest.get().getMark(markId).enqueue(new MyCallback<Mark>() {
                @Override
                public void onResponse(Call<Mark> call, Response<Mark> response) {
                    Mark m = response.body();
                    binding.setMark(m);
                }

                @Override
                public void onFailure(Call<Mark> call, Throwable t) {

                }
            });
        } else {
            long date = getIntent().getLongExtra(EXTRA_DATE, 0);
            Mark mark = new Mark(new Date(date));

            binding.setMark(mark);
        }

    }
}
