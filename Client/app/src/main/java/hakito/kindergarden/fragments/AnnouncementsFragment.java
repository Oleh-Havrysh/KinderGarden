package hakito.kindergarden.fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

import hakito.kindergarden.adapters.BaseBindableAdapter;
import hakito.kindergarden.model.Announcement;
import hakito.kindergarden.rest.ControllerImpl;
import kindergarden.hakito.kindergardenclient.BR;
import kindergarden.hakito.kindergardenclient.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AnnouncementsFragment extends Fragment {


    public AnnouncementsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_announcements, container, false);
        ListView listView = (ListView) v.findViewById(R.id.list_view);
        listView.setAdapter(new BaseBindableAdapter<Announcement>(v.getContext(), new ControllerImpl().getAnnouncements(), R.layout.announcement_item, BR.ann));
        return v;
    }

}
