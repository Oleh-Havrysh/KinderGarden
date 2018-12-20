package ua.nure.havrysh.kindergarten.adapters;

import android.content.Context;

import java.util.List;

import kindergarten.hakito.kindergartenclient.BR;
import kindergarten.hakito.kindergartenclient.R;
import ua.nure.havrysh.kindergarten.model.Announcement;


public class AnnouncementsAdapter extends BaseBindableAdapter<Announcement> {
    
    public AnnouncementsAdapter(Context context, List<Announcement> objects) {
        super(context, objects, R.layout.item_announcement, BR.ann);
    }
}
