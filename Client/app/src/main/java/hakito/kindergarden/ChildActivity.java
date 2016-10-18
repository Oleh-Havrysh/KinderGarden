package hakito.kindergarden;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Calendar;

import kindergarden.hakito.kindergardenclient.R;
import hakito.kindergarden.model.Child;
import hakito.kindergarden.rest.ControllerImpl;
import kindergarden.hakito.kindergardenclient.databinding.ActivityChildBinding;

public class ChildActivity extends AppCompatActivity {

    public static final String ARG_CHILD_ID="child_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityChildBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_child);

        Child child = new ControllerImpl().getChild(getIntent().getExtras().getInt(ARG_CHILD_ID));
        binding.setChild(child);

    }
}
