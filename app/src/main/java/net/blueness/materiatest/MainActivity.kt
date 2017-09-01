package net.blueness.materiatest

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.NavigationView
import android.support.design.widget.Snackbar
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.find
import org.jetbrains.anko.toast
import java.util.*

class MainActivity : AppCompatActivity() {

    var mDrawerLayout: DrawerLayout? = null

    val frutis = arrayOf(
            Fruit("Apple", R.drawable.apple),
            Fruit("Banana", R.drawable.banana),
            Fruit("Orange", R.drawable.orange),
            Fruit("Watermelon", R.drawable.watermelon),
            Fruit("Pear", R.drawable.pear),
            Fruit("Grape", R.drawable.grape),
            Fruit("Pineapple", R.drawable.pineapple),
            Fruit("Strawberry", R.drawable.strawberry),
            Fruit("Cherry", R.drawable.cherry),
            Fruit("Mango", R.drawable.mango)
            )

    private var fruitList = ArrayList<Fruit>()
    private var adapter: FruitAdapter? = null
    private var swipeRefresh: SwipeRefreshLayout? = null

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
                    .setAction("undo", {
                        toast("Data restored")
                    })
                    .show()
        }

        initFruits()
        val recyclerView: RecyclerView = find(R.id.recycler_view)
        val layoutManager = GridLayoutManager(this, 2)
        recyclerView.layoutManager = layoutManager
        adapter = FruitAdapter(fruitList)
        recyclerView.adapter = adapter

        swipeRefresh = find(R.id.swip_refresh)
        swipeRefresh?.setColorSchemeColors(R.color.colorPrimary)
        swipeRefresh?.setOnRefreshListener { refreshFruits() }
    }

    private fun refreshFruits() {
        object : Thread() {
            override fun run() {
                Thread.sleep(2000)
                runOnUiThread{
                    initFruits()
                    adapter?.notifyDataSetChanged()
                    swipeRefresh?.isRefreshing = false
                }
            }
        }.start()
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
            R.id.settings -> toast("You clicked Setting")
        }
        return true
    }

    private fun initFruits() {
        fruitList.clear()
        for (i in 0..49 ) {
            val random = Random()
            val index: Int = random.nextInt(frutis.size)
//            Log.i("index", frutis[index].name)
            fruitList.add(frutis[index])
        }

    }
}
