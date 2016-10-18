package hakito.kindergarden.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import hakito.kindergarden.ChildActivity;
import kindergarden.hakito.kindergardenclient.R;
import hakito.kindergarden.adapters.ChildrenAdapter;
import hakito.kindergarden.rest.ControllerImpl;

/**
 * Created by Oleg on 16.10.2016.
 */

public class ChildrenFragment extends Fragment implements AdapterView.OnItemClickListener {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_children, container, false);
        ListView l = (ListView) v.findViewById(R.id.list_view);
        l.setAdapter(new ChildrenAdapter(getActivity(), new ControllerImpl().getChildrens()));

        l.setOnItemClickListener(this);
        return v;
    }

    @Override
    public void onPause() {
        super.onPause();

        getView().setVisibility(View.INVISIBLE);
    }

    @Override
    public void onResume() {
        super.onResume();
        getView().setVisibility(View.VISIBLE);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent i = new Intent(getActivity(), ChildActivity.class);
        i.putExtra(ChildActivity.ARG_CHILD_ID, position);
        startActivity(i);
    }
}
