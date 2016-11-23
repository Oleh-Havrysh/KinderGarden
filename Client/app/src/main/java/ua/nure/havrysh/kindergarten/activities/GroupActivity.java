package ua.nure.havrysh.kindergarten.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import kindergarden.hakito.kindergardenclient.BR;
import kindergarden.hakito.kindergardenclient.R;
import kindergarden.hakito.kindergardenclient.databinding.ActivityGroupBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ua.nure.havrysh.kindergarten.adapters.BaseBindableAdapter;
import ua.nure.havrysh.kindergarten.model.Child;
import ua.nure.havrysh.kindergarten.model.Group;
import ua.nure.havrysh.kindergarten.model.Human;
import ua.nure.havrysh.kindergarten.rest.Rest;

public class GroupActivity extends AppCompatActivity {

    public static final String EXTRA_GROUP_ID = "group_id";
    ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActivityGroupBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_group);

        final long groupId = getIntent().getLongExtra(EXTRA_GROUP_ID, 0);
        final ListView listView = (ListView) findViewById(R.id.listView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(GroupActivity.this, ChildActivity.class);
                intent.putExtra(ChildActivity.ARG_CHILD_ID, ((Child) adapter.getItem(position)).getChild_id());
                startActivity(intent);
            }
        });

        if (groupId > 0) {
            Rest.get().getGroup(groupId).enqueue(new Callback<Group>() {
                @Override
                public void onResponse(Call<Group> call, Response<Group> response) {
                    final Group g = response.body();
                    binding.setGroup(g);

                    findViewById(R.id.text_teacher).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(GroupActivity.this, HumanActivity.class);
                            intent.putExtra(HumanActivity.EXTRA_HUMAN_ID, g.getTeacher_id());
                            startActivity(intent);
                        }
                    });

                    Rest.get().getHuman(g.getTeacher_id()).enqueue(new Callback<Human>() {
                        @Override
                        public void onResponse(Call<Human> call, Response<Human> response) {
                            ((TextView) findViewById(R.id.text_teacher)).setText(String.format("%s %s", response.body().getName(), response.body().getSurname()));
                        }

                        @Override
                        public void onFailure(Call<Human> call, Throwable t) {

                        }
                    });

                    Rest.get().getChildren(g.getGroup_id()).enqueue(new Callback<List<Child>>() {
                        @Override
                        public void onResponse(Call<List<Child>> call, Response<List<Child>> response) {

                            adapter = new BaseBindableAdapter<Child>(getApplicationContext(), response.body(), R.layout.item_child, BR.child);
                            listView.setAdapter(adapter);
                        }

                        @Override
                        public void onFailure(Call<List<Child>> call, Throwable t) {

                        }
                    });

                }

                @Override
                public void onFailure(Call<Group> call, Throwable t) {

                }
            });


        } else {
            binding.setGroup(new Group());
        }

    }
}
