package hakito.kindergarden;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Calendar;

import kindergarden.hakito.kindergardenclient.R;
import hakito.kindergarden.model.Child;
import hakito.kindergarden.rest.ControllerImpl;
import kindergarden.hakito.kindergardenclient.databinding.ActivityChildBinding;

public class ChildActivity extends AppCompatActivity {

    public static final String ARG_CHILD_ID = "child_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityChildBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_child);

        final Child child = new ControllerImpl().getChild(getIntent().getExtras().getInt(ARG_CHILD_ID));
        binding.setChild(child);

        findViewById(R.id.buttonParent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChildActivity.this, ParentActivity.class);
                intent.putExtra(ParentActivity.EXTRA_PARENT_ID, child.getParentId());
                startActivity(intent);
            }
        });
    }
}
