package hakito.kindergarden;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import hakito.kindergarden.model.Parent;
import hakito.kindergarden.rest.ControllerImpl;
import kindergarden.hakito.kindergardenclient.R;
import kindergarden.hakito.kindergardenclient.databinding.ActivityParentBinding;

public class ParentActivity extends AppCompatActivity {

    public static final String EXTRA_PARENT_ID = "parentId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityParentBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_parent);
        int parentId = getIntent().getIntExtra(EXTRA_PARENT_ID, -1);
        Parent p;
        if(parentId!=-1)
        {
           p = new ControllerImpl().getParent(parentId);
        }
        else
        {
                       p = new Parent();
        }

        binding.setParent(p);

    }
}
