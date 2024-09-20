package com.example.tictactoe.utils

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.stringResource
import androidx.recyclerview.widget.RecyclerView
import com.example.tictactoe.R
import com.example.tictactoe.data.OldGame
import com.example.tictactoe.databinding.DashboardItemBinding

class DashboardAdapter(private var oldGamesList: MutableList<OldGame>, private val context: Context) : RecyclerView.Adapter<DashboardAdapter.ItemHolder>() {

    fun setList(list: MutableList<OldGame>) {
        this.oldGamesList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val view = DashboardItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemHolder(view, context)
    }

    override fun getItemCount(): Int {
        return oldGamesList.size
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bind(oldGamesList[position], position)
    }

    inner class ItemHolder(val binding: DashboardItemBinding, val context: Context): RecyclerView.ViewHolder(binding.root) {
        fun bind(oldGame: OldGame, position: Int) {
            binding.firstPlayer.text = context.getString(R.string.player_won_message, oldGame.winSign)
            binding.root.setBackgroundColor(if(position % 2 == 0) Color.LightGray.toArgb() else Color.White.toArgb())
        }
    }
}
