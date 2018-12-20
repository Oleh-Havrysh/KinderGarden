package ua.nure.havrysh.kindergarten.activities;

import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import kindergarten.hakito.kindergartenclient.R;

/**
 * Created by Oleg on 20.12.2016.
 */

public class BaseEditingActivity extends AppCompatActivity {

    public boolean isEditableActivity() {
        return true;
    }

    public void onCancel() {
        finish();
    }

    public void onSave() {
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.base_editing, menu);
        if (!isEditableActivity()) {
            menu.setGroupVisible(R.id.ediitng, false);
        }

        menu.findItem(R.id.cancel).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                onCancel();
                return true;
            }
        });

        menu.findItem(R.id.save).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                onSave();
                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}
