package net.blueness.materiatest

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.design.widget.Snackbar
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.find
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {

    var mDrawerLayout: DrawerLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = find(R.id.toolbar)
        setSupportActionBar(toolbar)

        mDrawerLayout = find(R.id.drawer_layout)
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu)
        }

        val navView: NavigationView = find(R.id.nav_view)
        navView.setCheckedItem(R.id.nav_call)
        navView.setNavigationItemSelectedListener{
            mDrawerLayout!!.closeDrawers()
            true
        }

//        偷懒了偷懒了 find你妹
        fab.setOnClickListener{
//            toast("FAB clicked")
            v: View ->
            Snackbar.make(v, "Data Deletd", Snackbar.LENGTH_SHORT)
                    .setAction("undo", View.OnClickListener {
                        toast("Data restored")
                    })
                    .show()
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> mDrawerLayout?.openDrawer(GravityCompat.START)
            R.id.backup -> toast("You clicked Backup")
            R.id.delete -> toast("You clicked Delete")
            R.id.setting -> toast("You clicked Setting")
        }
        return true
    }
}
