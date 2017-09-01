package net.blueness.materiatest

import android.content.Context
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import org.jetbrains.anko.find


/**
 * Created by Blueness on 2017/9/1.
 */

class FruitAdapter(val fruitList: MutableList<Fruit>):
        RecyclerView.Adapter<FruitAdapter.ViewHolder>() {

    class ViewHolder(val v: View): RecyclerView.ViewHolder(v) {
        val cardView: CardView = v as CardView
        val fruitImage: ImageView = v.find(R.id.fruit_image)
        val fruitName: TextView = v.find(R.id.fruit_name)
    }

    private var mContext: Context? = null
    private var mFruitList: MutableList<Fruit>? = null

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val fruit = mFruitList?.get(position)
        holder?.fruitName?.text = fruit?.name
        Glide.with(mContext).load(fruit?.imageId).into(holder?.fruitImage)
    }

    override fun getItemCount(): Int {
        return mFruitList!!.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        if(mContext == null && parent != null){
            mContext = parent.context
        }
        val view: View = LayoutInflater.from(mContext).inflate(R.layout.fruit_item, parent, false)
        return ViewHolder(view)
    }

}