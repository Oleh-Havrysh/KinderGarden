package ua.nure.havrysh.kindergarten.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import kindergarten.hakito.kindergartenclient.R
import kindergarten.hakito.kindergartenclient.databinding.NavHeaderMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ua.nure.havrysh.kindergarten.App
import ua.nure.havrysh.kindergarten.fragments.AnnouncementsFragment
import ua.nure.havrysh.kindergarten.fragments.GroupsFragment
import ua.nure.havrysh.kindergarten.fragments.LandingFragment
import ua.nure.havrysh.kindergarten.fragments.MyChildrenFragment
import ua.nure.havrysh.kindergarten.rest.AccessTokenStorage
import ua.nure.havrysh.kindergarten.rest.Rest

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (AccessTokenStorage(this).getToken().isEmpty()) {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
            return
        }

        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
                this,
                drawer_layout,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        )
        drawer_layout.setDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        loadProfile()

        showRoleMenuItems()

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.content_main, LandingFragment())
                    .commit()
        }
    }

    private fun showRoleMenuItems() {
        when (AccessTokenStorage.role) {
            AccessTokenStorage.Role.TEACHER -> {
                nav_view.menu.findItem(R.id.menu_my_children).isVisible = false
            }

            AccessTokenStorage.Role.PARENT -> {
                nav_view.menu.findItem(R.id.menu_groups).isVisible = false
            }
        }
    }

    private fun loadProfile() {
        App.scope.launch(Dispatchers.IO) {
            val self = Rest.get().getSelf().await()

            withContext(Dispatchers.Main) {
                NavHeaderMainBinding.bind(nav_view.getHeaderView(0)).setUser(self);
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_LOGIN) {
            if (resultCode == Activity.RESULT_OK) {
                loadProfile()
            } else {
                finish()
            }
        }

        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId


        return if (id == R.id.action_refresh) {
            true
        } else super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        val transaction = supportFragmentManager.beginTransaction()
        if (id == R.id.menu_announcements) {
            transaction.replace(R.id.content_main, AnnouncementsFragment())
        } else if (id == R.id.menu_groups) {
            transaction.replace(R.id.content_main, GroupsFragment())
        } else if (id == R.id.menu_my_children) {
            transaction.replace(R.id.content_main, MyChildrenFragment())
        } else if (id == R.id.menu_logout) {
            AccessTokenStorage(this).setToken("")
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
        transaction.commit()

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    companion object {

        val REQUEST_LOGIN = 1
    }
}
