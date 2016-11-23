package ua.nure.havrysh.kindergarten.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import kindergarden.hakito.kindergardenclient.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ua.nure.havrysh.kindergarten.model.Child;
import kindergarden.hakito.kindergardenclient.databinding.ActivityChildBinding;
import ua.nure.havrysh.kindergarten.rest.Rest;

public class ChildActivity extends AppCompatActivity {

    public static final String ARG_CHILD_ID = "child_id";

    Child child;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActivityChildBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_child);

        Rest.get().getChild(getIntent().getLongExtra(ARG_CHILD_ID, 0)).enqueue(new Callback<Child>() {
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
    }
}
