package com.innocent.chuck.jokes.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.innocent.chuck.core.extensions.capitalizeWords
import com.innocent.chuck.jokes.R
import com.innocent.chuck.jokes.commons.dto.Joke

class JokeDetailFragment : Fragment(), DetailsActivity.Interaction {

    private var category: String? = null

    private lateinit var details: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (activity as DetailsActivity?)?.interaction = this

        arguments?.let {
            if (it.containsKey(ARG_JOKE_CATEGORY)) {
                category = it.getString(ARG_JOKE_CATEGORY)
                activity?.findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout)?.title =
                    category?.capitalizeWords()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(R.layout.item_detail, container, false)
        details = rootView.findViewById<TextView>(R.id.item_detail)
        return rootView
    }

    companion object {
        const val ARG_JOKE_CATEGORY = "joke_category"
    }

    override fun jokeLoaded(joke: Joke) {
        details.text = joke.value
    }
}