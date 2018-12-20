package ua.nure.havrysh.kindergarten.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import kindergarten.hakito.kindergartenclient.BR;
import kindergarten.hakito.kindergartenclient.R;
import retrofit2.Call;
import retrofit2.Response;
import ua.nure.havrysh.kindergarten.activities.GroupActivity;
import ua.nure.havrysh.kindergarten.adapters.BaseBindableAdapter;
import ua.nure.havrysh.kindergarten.model.Child;
import ua.nure.havrysh.kindergarten.model.Group;
import ua.nure.havrysh.kindergarten.model.Human;
import ua.nure.havrysh.kindergarten.rest.MyCallback;
import ua.nure.havrysh.kindergarten.rest.Rest;

/**
 * A simple {@link Fragment} subclass.
 */
public class GroupsFragment extends Fragment {


    ListAdapter adapter;

    public GroupsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_groups, container, false);
        final ListView listView = (ListView) v.findViewById(R.id.listView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), GroupActivity.class);
                intent.putExtra(GroupActivity.EXTRA_GROUP_ID, ((Group) adapter.getItem(position)).getGroup_id());
                startActivity(intent);
            }
        });

        Rest.get().getGroups().enqueue(new MyCallback<List<Group>>() {
            @Override
            public void onResponse(Call<List<Group>> call, final Response<List<Group>> response) {

                adapter = new BaseBindableAdapter<Group>(getActivity(), response.body(), R.layout.item_group, BR.group) {
                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {


                        final View v = super.getView(position, convertView, parent);
                        Group g = (Group) adapter.getItem(position);
                        Rest.get().getHuman(g.getTeacher_id()).enqueue(new MyCallback<Human>() {
                            @Override
                            public void onResponse(Call<Human> call, Response<Human> response) {
                                Human h = response.body();
                                ((TextView) v.findViewById(R.id.text_teacher)).setText(String.format("%s %s", h.getName(), h.getSurname()));
                            }

                            @Override
                            public void onFailure(Call<Human> call, Throwable t) {

                            }
                        });

                        Rest.get().getChildren(g.getGroup_id()).enqueue(new MyCallback<List<Child>>() {
                            @Override
                            public void onResponse(Call<List<Child>> call, Response<List<Child>> response) {
                                ((TextView) v.findViewById(R.id.text_childrenCount)).setText(String.format("%d childrens", response.body().size()));
                            }

                            @Override
                            public void onFailure(Call<List<Child>> call, Throwable t) {

                            }
                        });

                        return v;
                    }
                };
                listView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Group>> call, Throwable t) {

            }
        });
        return v;
    }

}
