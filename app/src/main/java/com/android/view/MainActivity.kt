package com.android.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.koinapp.R
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    val viewModel: MainViewModel by viewModel()

    val usersAdapter: UsersAdapter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecycler()
        viewModel.loadUsers()
        viewModel.liveData.observe(this, Observer { state ->
            when (state) {
                is MainViewState.StateOnProgress -> {
                    progress.visibility = View.VISIBLE
                }
                is MainViewState.StateOnSuccess -> {
                    progress.visibility = View.GONE
                    usersAdapter.setData(state.users)
                }
                is MainViewState.StateOnError -> {
                    progress.visibility = View.GONE
                    Toast.makeText(this, state.msg, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun initRecycler() {
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = usersAdapter
    }
}
