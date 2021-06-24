package com.example.myapplication.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.adapters.RVAdapter
import com.example.myapplication.utils.DataState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    @Inject
    lateinit var rvAdapter: RVAdapter

    @Inject
    lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager = linearLayoutManager

        viewModel.dataState.observe(this, Observer { dataState ->
            when (dataState) {
                is DataState.Success -> {
                    progressBar.visibility = View.GONE
                    textView.visibility = View.GONE
                    recyclerView.visibility = View.VISIBLE
                    recyclerView.adapter = rvAdapter
                    rvAdapter.setList(dataState.users)
                    rvAdapter.notifyDataSetChanged()
                    Log.i("Debug", "in data state success")
                }

                is DataState.Error -> {
                    progressBar.visibility = View.GONE
                    recyclerView.visibility = View.GONE
                    textView.text = dataState.error
                    textView.visibility = View.VISIBLE
                    Toast.makeText(this, dataState.error, Toast.LENGTH_LONG).show()
                    Log.i("Debug", "in data state error")
                }

                is DataState.Loading -> {
                    progressBar.visibility = View.VISIBLE
                    recyclerView.visibility = View.GONE
                    textView.visibility = View.GONE
                    Log.i("Debug", "in data state loading")
                }
            }
        })
    }
}