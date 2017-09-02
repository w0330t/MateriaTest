package net.blueness.materiatest

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.CollapsingToolbarLayout
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.support.v7.widget.Toolbar
import com.bumptech.glide.Glide
import org.jetbrains.anko.find

class FruitActivity : AppCompatActivity() {

    companion object {
        val FRUIT_NAME: String = "fruit_name"
        val FRUIT_IMAGE_ID: String = "fruit_image_id"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fruit)

        val intent = intent
        val fruitName = intent.getStringExtra(FRUIT_NAME)
        val fruitImageId = intent.getIntExtra(FRUIT_IMAGE_ID, 0)
        val toolbar: Toolbar = find(R.id.toolbar)
        val collapsingToolbar: CollapsingToolbarLayout = find(R.id.collapsing_toolbar)
        val fruitImageView: ImageView = find(R.id.fruit_image_view)
        val fruitContentText: TextView = find(R.id.fruit_content_text)

        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
        }
        collapsingToolbar.title = fruitName
        Glide.with(this).load(fruitImageId).into(fruitImageView)
        val fruitContent = generateFRuitContent(fruitName)
        fruitContentText.text = fruitContent
    }

    private fun generateFRuitContent(fruitName: String?): String {
        var fruitContent = StringBuilder()
        for (i in 0..499 ) {
            fruitContent.append(fruitName)
        }
        return fruitContent.toString()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home ->{
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
