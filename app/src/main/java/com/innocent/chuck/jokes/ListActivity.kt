package com.innocent.chuck.jokes

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.widget.NestedScrollView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.innocent.chuck.core.application.BaseActivity
import com.innocent.chuck.core.service.ApiResult
import com.innocent.chuck.jokes.commons.JokesDH
import com.innocent.chuck.jokes.dummy.DummyContent
import com.innocent.chuck.jokes.list.viewmodel.ListViewModel
import com.innocent.chuck.jokes.list.viewmodel.ListViewModelFactory
import java.io.IOException
import javax.inject.Inject

class ListActivity : BaseActivity() {

    private var twoPane: Boolean = false
    private val component by lazy { JokesDH.listComponent() }

    @Inject
    lateinit var viewModelFactory: ListViewModelFactory

    private val viewModel: ListViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(ListViewModel::class.java)
    }

    private val context: Context by lazy { this }

    private val TAG = "ListActivity"

    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_list)
        component.inject(this)
        initViews()
        viewModel.fetchCategories()
        initiateDataListener()
    }

    private fun initViews(){
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar.title = title

        progressBar = findViewById<ProgressBar>(R.id.activityIndicator)

        if (findViewById<NestedScrollView>(R.id.item_detail_container) != null) {
            twoPane = true
        }

    }

    private fun initiateDataListener() {
        viewModel.fetchCategoryOutcome.observe(this, Observer<ApiResult<List<String>>>{ outcome ->
            Log.e(TAG, "initiateDataListener: $outcome")

            when (outcome){
                is ApiResult.Progress -> {
                    if (outcome.loading)
                        progressBar.visibility = View.VISIBLE
                    else
                        progressBar.visibility = View.GONE
                }

                is ApiResult.Success -> {
                    setupRecyclerView(findViewById(R.id.item_list), outcome.data)
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
                            "Failed to load jokes categories. Please try again later",
                            Toast.LENGTH_LONG
                        ).show()
                }
            }
        })
    }

    private fun setupRecyclerView(recyclerView: RecyclerView, data: List<String>) {
        recyclerView.adapter = CategoryListAdapter(this, data, twoPane)
    }
}