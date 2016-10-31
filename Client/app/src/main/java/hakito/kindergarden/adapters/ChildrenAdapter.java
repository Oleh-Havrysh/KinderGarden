package hakito.kindergarden.adapters;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import kindergarden.hakito.kindergardenclient.BR;
import kindergarden.hakito.kindergardenclient.R;
import hakito.kindergarden.model.Child;

/**
 * Created by Oleg on 16.10.2016.
 */

public class ChildrenAdapter extends BaseBindableAdapter<Child> {

    public ChildrenAdapter(Context context, List<Child> objects) {
        super(context, objects, R.layout.child_item, BR.child);
    }

}
