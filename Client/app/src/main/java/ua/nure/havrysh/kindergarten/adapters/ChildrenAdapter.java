package ua.nure.havrysh.kindergarten.adapters;

import android.content.Context;

import java.util.List;

import kindergarden.hakito.kindergardenclient.BR;
import kindergarden.hakito.kindergardenclient.R;
import ua.nure.havrysh.kindergarten.model.Child;

/**
 * Created by Oleg on 16.10.2016.
 */

public class ChildrenAdapter extends BaseBindableAdapter<Child> {

    public ChildrenAdapter(Context context, List<Child> objects) {
        super(context, objects, R.layout.item_child, BR.child);
    }

}
