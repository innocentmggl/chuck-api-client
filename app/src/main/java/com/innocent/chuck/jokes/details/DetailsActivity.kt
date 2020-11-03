package com.innocent.chuck.jokes.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.innocent.chuck.core.application.BaseActivity
import com.innocent.chuck.core.service.ApiResult
import com.innocent.chuck.jokes.ListActivity
import com.innocent.chuck.jokes.R
import com.innocent.chuck.jokes.commons.JokesDH
import com.innocent.chuck.jokes.commons.dto.Joke
import com.innocent.chuck.jokes.details.viewmodel.JokeViewModel
import com.innocent.chuck.jokes.details.viewmodel.JokeViewModelFactory
import com.squareup.picasso.Picasso
import java.io.IOException
import javax.inject.Inject

class DetailsActivity : BaseActivity() {

    private val component by lazy { JokesDH.detailsComponent() }

    @Inject
    lateinit var viewModelFactory: JokeViewModelFactory

    @Inject
    lateinit var picasso: Picasso

    private val viewModel: JokeViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(JokeViewModel::class.java)
    }

    private val context: Context by lazy { this }

    private val TAG = "DetailsActivity"

    private lateinit var progressBar: ProgressBar
    private lateinit var iconImageView: ImageView

    var interaction: Interaction? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_detail)
        component.inject(this)
        initViews()

        if (savedInstanceState == null) {
            setupDetailsFragment()
        }
        viewModel.fetchJokeFor(intent.getStringExtra(JokeDetailFragment.ARG_JOKE_CATEGORY))
        initiateDataListener()
    }

    private fun initViews(){
        setSupportActionBar(findViewById(R.id.detail_toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        progressBar = findViewById(R.id.progressBar)
        iconImageView = findViewById(R.id.chuckImage)
    }

    private fun setupDetailsFragment(){
        val fragment = JokeDetailFragment().apply {
            arguments = Bundle().apply {
                putString(
                    JokeDetailFragment.ARG_JOKE_CATEGORY,
                    intent.getStringExtra(JokeDetailFragment.ARG_JOKE_CATEGORY))
            }
        }

        supportFragmentManager.beginTransaction()
            .add(R.id.item_detail_container, fragment)
            .commit()
    }

    private fun initiateDataListener() {

        viewModel.fetchJokeOutcome.observe(this, Observer<ApiResult<Joke>>{ outcome ->
            Log.e(TAG, "initiateDataListener: $outcome")

            when (outcome){
                is ApiResult.Progress -> {
                    if (outcome.loading)
                        progressBar.visibility = View.VISIBLE
                    else
                        progressBar.visibility = View.GONE
                }

                is ApiResult.Success -> {
                    handleJokeResponse(outcome.data)
                }

                is ApiResult.Failure -> {
                    if (outcome.e is IOException)
                        Toast.makeText(
                            context,
                            "No internet connection",
                            Toast.LENGTH_LONG
                        ).show()
                    else
                        Toast.makeText(
                            context,
                            "Failed to load joke. Please try again later",
                            Toast.LENGTH_LONG
                        ).show()
                }
            }
        })
    }

    interface Interaction {
        fun jokeLoaded(joke: Joke)
    }

    private fun handleJokeResponse(joke: Joke){
        picasso.load(joke.iconUrl).into(iconImageView)
        interaction?.jokeLoaded(joke)
    }

    override fun onOptionsItemSelected(item: MenuItem) =
            when (item.itemId) {
                android.R.id.home -> {
                    navigateUpTo(Intent(this, ListActivity::class.java))
                    true
                }
                else -> super.onOptionsItemSelected(item)
            }
}