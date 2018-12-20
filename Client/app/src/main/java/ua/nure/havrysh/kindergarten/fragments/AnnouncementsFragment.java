package ua.nure.havrysh.kindergarten.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import kindergarten.hakito.kindergartenclient.BR;
import kindergarten.hakito.kindergartenclient.R;
import retrofit2.Call;
import retrofit2.Response;
import ua.nure.havrysh.kindergarten.activities.AnnouncementActivity;
import ua.nure.havrysh.kindergarten.adapters.BaseBindableAdapter;
import ua.nure.havrysh.kindergarten.model.Announcement;
import ua.nure.havrysh.kindergarten.rest.MyCallback;
import ua.nure.havrysh.kindergarten.rest.Rest;

/**
 * A simple {@link Fragment} subclass.
 */
public class AnnouncementsFragment extends Fragment {
    ListView listView;

    public AnnouncementsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();
        listView.invalidateViews();
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_announcements, container, false);

        v.findViewById(R.id.fab_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AnnouncementActivity.class);
                startActivity(intent);
            }
        });

        listView = (ListView) v.findViewById(R.id.list_view);
        Rest.get().getAnnouncements().enqueue(new MyCallback<List<Announcement>>() {
            @Override
            public void onResponse(Call<List<Announcement>> call, Response<List<Announcement>> response) {
                listView.setAdapter(new BaseBindableAdapter<>(v.getContext(), response.body(), R.layout.item_announcement, BR.ann));
            }

            @Override
            public void onFailure(Call<List<Announcement>> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(v.getContext(), AnnouncementActivity.class);
                intent.putExtra(AnnouncementActivity.EXTRA_ANN, ((Announcement)listView.getAdapter().getItem(position)).getAnnouncement_id());
                startActivity(intent);

            }
        });
        return v;
    }

}
