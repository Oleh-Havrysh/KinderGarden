package ua.nure.havrysh.kindergarten.activities;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import kindergarten.hakito.kindergartenclient.R;
import kindergarten.hakito.kindergartenclient.databinding.NavHeaderMainBinding;
import ua.nure.havrysh.kindergarten.LoginHelper;
import ua.nure.havrysh.kindergarten.fragments.AnnouncementsFragment;
import ua.nure.havrysh.kindergarten.fragments.GroupsFragment;
import ua.nure.havrysh.kindergarten.fragments.SettingsFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static final int REQUEST_LOGIN = 1;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!LoginHelper.INSTANCE.loggedIn(this)) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivityForResult(intent, REQUEST_LOGIN);
        }

        setContentView(R.layout.activity_main);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();


        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        NavHeaderMainBinding.bind(navigationView.getHeaderView(0)).setUser(LoginHelper.INSTANCE.getUser());


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUEST_LOGIN) {
            if (resultCode == RESULT_OK) {
                try {
                    NavHeaderMainBinding.bind(navigationView.getHeaderView(0)).setUser(LoginHelper.INSTANCE.getUser());
                } catch (RuntimeException e) {
                }
            } else {
                finish();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_refresh) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        if (id == R.id.menu_announcements) {
            transaction.replace(R.id.content_main, new AnnouncementsFragment());
        } else if (id == R.id.menu_groups) {
            transaction.replace(R.id.content_main, new GroupsFragment());
        } else if (id == R.id.menu_settings) {
            transaction.replace(R.id.content_main, new SettingsFragment());
        } else if (id == R.id.menu_logout) {
            finish();
        }

        transaction.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
