package hakito.kindergarden.adapters;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import kindergarden.hakito.kindergardenclient.R;
import hakito.kindergarden.model.Child;

/**
 * Created by Oleg on 16.10.2016.
 */

public class ChildrenAdapter extends BaseAdapter<Child> {


    public ChildrenAdapter(Context context, List<Child> objects) {
        super(context, objects, R.layout.child_item);
    }

    @Override
    protected void fillView(View view, Child obj) {
        ((TextView)view.findViewById(R.id.child_item_name)).setText(obj.getFullName());
    }
}
