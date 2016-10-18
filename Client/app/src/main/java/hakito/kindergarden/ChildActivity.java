package hakito.kindergarden;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Calendar;

import kindergarden.hakito.kindergardenclient.R;
import hakito.kindergarden.model.Child;
import hakito.kindergarden.rest.ControllerImpl;

public class ChildActivity extends AppCompatActivity {

    public static final String ARG_CHILD_ID="child_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child);


        Child child = new ControllerImpl().getChild(getIntent().getExtras().getInt(ARG_CHILD_ID));
        ((TextView) findViewById(R.id.text_name)).setText(child.getFullName());
        ((TextView) findViewById(R.id.text_birthdate)).setText(""+child.getBirthDate().get(Calendar.YEAR));
        ((TextView) findViewById(R.id.text_notice)).setText(child.getNotices());
    }
}
