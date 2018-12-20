package ua.nure.havrysh.kindergarten.adapters;

import android.content.Context;

import java.util.List;

import kindergarten.hakito.kindergartenclient.BR;
import kindergarten.hakito.kindergartenclient.R;
import ua.nure.havrysh.kindergarten.model.Group;

/**
 * Created by Oleg on 16.10.2016.
 */

public class GroupsAdapter extends BaseBindableAdapter<Group> {
    
    public GroupsAdapter(Context context, List<Group> objects) {
        super(context, objects, R.layout.item_group, BR.group);
    }
    
}
