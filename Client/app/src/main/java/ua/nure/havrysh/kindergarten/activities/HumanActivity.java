package ua.nure.havrysh.kindergarten.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import kindergarden.hakito.kindergardenclient.R;
import kindergarden.hakito.kindergardenclient.databinding.ActivityHumanBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ua.nure.havrysh.kindergarten.model.Human;
import ua.nure.havrysh.kindergarten.rest.Rest;

public class HumanActivity extends AppCompatActivity {

    public static final String EXTRA_HUMAN_ID = "parentId";

    Human h;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActivityHumanBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_human);
        long humanId = getIntent().getLongExtra(EXTRA_HUMAN_ID, -1);

        if (humanId != -1) {
            Rest.get().getHuman(humanId).enqueue(new Callback<Human>() {
                @Override
                public void onResponse(Call<Human> call, Response<Human> response) {
                    h = response.body();
                    binding.setHuman(h);
                }

                @Override
                public void onFailure(Call<Human> call, Throwable t) {

                }
            });
        } else {
            h = new Human();
            binding.setHuman(h);
        }


    }
}
