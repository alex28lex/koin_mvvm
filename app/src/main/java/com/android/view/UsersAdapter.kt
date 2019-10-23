package com.android.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.koinapp.R
import com.android.koinapp.model.UserModel
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.item_git_user.view.*


class UsersAdapter : RecyclerView.Adapter<UsersAdapter.UserViewHolder>() {
    private var dataList: MutableList<UserModel> = ArrayList()
    private lateinit var clickedUserConsumer: Consumer<UserModel>


    fun setClickedUserListener(listener: Consumer<UserModel>) {
        clickedUserConsumer = listener
    }

    fun setData(data: List<UserModel>) {
        this.dataList.clear()
        this.dataList.addAll(data)
        notifyDataSetChanged()
    }


    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bindView(dataList[position])
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_git_user, parent, false)
        return UserViewHolder(view)
    }

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(user: UserModel) {
            itemView.userNameText.text = user.login

            itemView.setOnClickListener { clickedUserConsumer.accept(dataList[adapterPosition]) }
        }
    }
}