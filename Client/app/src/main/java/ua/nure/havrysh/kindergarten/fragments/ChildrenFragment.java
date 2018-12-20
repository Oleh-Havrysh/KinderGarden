package ua.nure.havrysh.kindergarten.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import kindergarten.hakito.kindergartenclient.R;
import ua.nure.havrysh.kindergarten.activities.ChildActivity;

/**
 * Created by Oleg on 16.10.2016.
 */

public class ChildrenFragment extends Fragment implements AdapterView.OnItemClickListener {

    ListView listView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_children, container, false);

        listView = (ListView) v.findViewById(R.id.list_view);

        //listView.setAdapter(new ChildrenAdapter(getActivity(), new ControllerImpl().getChildrens()));

        listView.setOnItemClickListener(this);
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        listView.invalidateViews();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent i = new Intent(getActivity(), ChildActivity.class);
        i.putExtra(ChildActivity.ARG_CHILD_ID, position);
        startActivity(i);
    }
}
