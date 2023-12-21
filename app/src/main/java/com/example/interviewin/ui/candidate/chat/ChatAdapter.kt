package com.example.interviewin.ui.candidate.chat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.interviewin.databinding.ItemBotChatBinding
import com.example.interviewin.databinding.ItemUserChatBinding

class ChatAdapter(
    private val items: List<ListChat>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    inner class UserChatViewHolder(private val binding: ItemUserChatBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ListChat.UserChat) {
            binding.tvUserChatUsername.text = item.username
            binding.tvUserChat.text = item.message
        }
    }

    inner class BotChatViewHolder(private val binding: ItemBotChatBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ListChat.BotChat) {
            binding.tvBotChat.text = item.message
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is ListChat.UserChat -> 0
            is ListChat.BotChat -> 1
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            0 -> UserChatViewHolder(
                ItemUserChatBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
            1 -> BotChatViewHolder(
                ItemBotChatBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val item = items[position]) {
            is ListChat.UserChat -> (holder as UserChatViewHolder).bind(item)
            is ListChat.BotChat -> (holder as BotChatViewHolder).bind(item)
        }
    }
}