package ca.ulaval.ima.tp2

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import ca.ulaval.ima.tp2.ui.abacus.AbacusFragment
import ca.ulaval.ima.tp2.ui.infos.FormFragment
import ca.ulaval.ima.tp2.ui.infos.InfosFragment
import ca.ulaval.ima.tp2.ui.infos.ProfileFragment
import ca.ulaval.ima.tp2.ui.internet.InternetFragment
import java.util.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var appBarConfiguration: AppBarConfiguration
    lateinit var profile: Profile

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        var birth : Calendar = Calendar.getInstance();
        birth.set(Calendar.YEAR, 1999)
        birth.set(Calendar.MONTH, Calendar.MARCH)
        birth.set(Calendar.DAY_OF_MONTH, 11)
        profile = Profile(
                "Adam",
                "Cohen",
                birth,
                "opt_male",
                "GIF",
                false
        )


        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)

        val toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()


        val navView: NavigationView = findViewById(R.id.nav_view)
        navView.setNavigationItemSelectedListener(this)

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.nav_host_fragment, InfosFragment())
        transaction.commit()
        supportActionBar?.title = getString(R.string.menu_info)
    }

    override fun onBackPressed() {
        val drawer = findViewById<View?>(R.id.drawer_layout) as DrawerLayout
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_internet -> {
                val transaction = supportFragmentManager.beginTransaction()

                transaction.replace(R.id.nav_host_fragment, InternetFragment())
                transaction.commit()
                supportActionBar?.title = getString(R.string.menu_internet_status)
            }
            R.id.nav_info -> {
                val transaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.nav_host_fragment, InfosFragment())
                transaction.commit()
                supportActionBar?.title = getString(R.string.menu_info)
            }
            R.id.nav_abacus -> {
                val transaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.nav_host_fragment, AbacusFragment())
                transaction.commit()
                supportActionBar?.title = getString(R.string.menu_abacus)
            }
            R.id.nav_form -> {
                val transaction = supportFragmentManager.beginTransaction()
                val formFragment =  FormFragment();
                val args = Bundle();
                args.putParcelable("PROFILE", profile)
                formFragment.arguments = args
                transaction.replace(R.id.nav_host_fragment, formFragment)
                transaction.commit()
                supportActionBar?.title = getString(R.string.menu_form)
            }
            R.id.nav_profile -> {
                val transaction = supportFragmentManager.beginTransaction()
                val profileFragment = ProfileFragment();
                val args = Bundle();
                args.putParcelable("PROFILE", profile)
                profileFragment.arguments = args
                transaction.replace(R.id.nav_host_fragment, profileFragment)
                transaction.commit()
                supportActionBar?.title = getString(R.string.menu_profile)
            }
        }
        val drawer = findViewById<View?>(R.id.drawer_layout) as DrawerLayout
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

}