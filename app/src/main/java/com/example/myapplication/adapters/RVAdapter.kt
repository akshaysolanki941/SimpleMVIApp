package com.example.myapplication.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.User
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject

class RVAdapter
@Inject
constructor(
    @ActivityContext val context: Context
) : RecyclerView.Adapter<RVAdapter.ViewHolder>() {

    private var users: List<User> = emptyList()

    public fun setList(users: List<User>) {
        this.users = users
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tv_id: TextView = itemView.findViewById(R.id.tv_id)
        val tv_name: TextView = itemView.findViewById(R.id.tv_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = users[position]
        holder.tv_id.text = user.id
        holder.tv_name.text = user.name
    }

    override fun getItemCount(): Int {
        return users.size
    }
}