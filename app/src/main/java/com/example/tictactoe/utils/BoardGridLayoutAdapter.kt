package com.example.tictactoe.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tictactoe.R
import com.example.tictactoe.data.BoardCell
import com.example.tictactoe.databinding.BoardFieldItemBinding

class BoardGridLayoutAdapter(private val onClickListener: (i: Int, j:Int) -> Unit): RecyclerView.Adapter<BoardGridLayoutAdapter.FieldHolder>() {

    private var board = mutableListOf<MutableList<BoardCell>>()
    private var endOfGame = false

    fun setBoard(board: MutableList<MutableList<BoardCell>>) {
        this.board = board
        notifyDataSetChanged()
    }

    fun setEndOfGame(endOfGame: Boolean) {
        this.endOfGame = endOfGame
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FieldHolder {
        val view = BoardFieldItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FieldHolder(view, onClickListener)
    }

    override fun getItemCount(): Int {
        return board.size * board[0].size
    }

    override fun onBindViewHolder(holder: FieldHolder, position: Int) {
        holder.bind(board[position/3][position%3].value)
        if(!endOfGame) {
            holder.binding.fieldImage.setOnClickListener{
                onClickListener(position/3, position%3)
            }
        }
    }

    inner class FieldHolder(val binding: BoardFieldItemBinding, private val onClickListener: (i: Int, j: Int) -> Unit): RecyclerView.ViewHolder(binding.root) {
        fun bind(value: Char) {

            when (value) {
                'o' -> {
                    binding.fieldImage.setImageResource(R.drawable.icon_circle)
                }
                'x' -> {
                    binding.fieldImage.setImageResource(R.drawable.icon_cross)
                }
                else -> {
                    binding.fieldImage.setImageResource(R.drawable.icon_blank)
                }
            }
//            itemView.findViewById<ImageButton>(R.id.field_image).setImageResource(R.drawable.icon_cross)
        }
    }
}