package com.innocent.chuck.jokes

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.innocent.chuck.core.extensions.capitalizeWords
import com.innocent.chuck.jokes.details.DetailsActivity
import com.innocent.chuck.jokes.details.JokeDetailFragment

class CategoryListAdapter(private val parentActivity: ListActivity,
                          private val values: List<String>,
                          private val twoPane: Boolean) :
    RecyclerView.Adapter<CategoryListAdapter.ViewHolder>() {

    private val onClickListener: View.OnClickListener

    init {
        onClickListener = View.OnClickListener { v ->
            val item = v.tag as String

            if (twoPane) {
                val fragment = JokeDetailFragment().apply {
                    arguments = Bundle().apply {
                        putString(JokeDetailFragment.ARG_JOKE_CATEGORY, item)
                    }
                }
                parentActivity.supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.item_detail_container, fragment)
                    .commit()
            } else {
                val intent = Intent(v.context, DetailsActivity::class.java).apply {
                    putExtra(JokeDetailFragment.ARG_JOKE_CATEGORY, item)
                }
                v.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_content, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.categoryName.text = item.capitalizeWords()

        with(holder.itemView) {
            tag = item
            setOnClickListener(onClickListener)
        }
    }

    override fun getItemCount() = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val categoryName: TextView = view.findViewById(R.id.category_name)
    }
}