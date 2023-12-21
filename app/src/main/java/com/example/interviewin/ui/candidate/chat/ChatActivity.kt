package com.example.interviewin.ui.candidate.chat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.interviewin.R
import com.example.interviewin.data.ResultState
import com.example.interviewin.data.api.response.ChatItem
import com.example.interviewin.data.api.response.ChatResponse
import com.example.interviewin.databinding.ActivityChatBinding
import com.example.interviewin.factory.ViewModelFactory

class ChatActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChatBinding
    private val chatViewModel by viewModels<ChatViewModel> {
        ViewModelFactory.getInstance(this)
    }

    private lateinit var adapter: ChatAdapter
    private var listChat = mutableListOf<ListChat>()
    private var nextRequest: ChatResponse? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = ChatAdapter(listChat)
        val recyclerView = binding.rvChat
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    private fun generateFirstQuestion(request: ChatResponse) {
        chatViewModel.generateFirstQuestion(request).observe(this) { result ->
            if (result != null) {
                when (result) {
                    is ResultState.Loading -> {
                        showLoading(true)
                    }

                    is ResultState.Success -> {
                        showLoading(false)
                        addBotChat(result.data.question)
                        adapter.notifyDataSetChanged()
                        val chatItem = ChatItem(result.data.question, "")
                        nextRequest = ChatResponse(request.interviewId, listOf(chatItem))
                    }

                    is ResultState.Error -> {
                        showLoading(false)
                        showToast(result.error)
                    }
                }
            }
        }
    }

    private fun generateQuestion(request: ChatResponse) {
        chatViewModel.generateQuestion(request).observe(this) { result ->
            if (result != null) {
                when (result) {
                    is ResultState.Loading -> {
                        showLoading(true)
                    }

                    is ResultState.Success -> {
                        listChat.clear()
                        showLoading(false)
                        if (listChat.isEmpty()) {
                            populateListChat(result.data)
                        }
                        adapter.notifyDataSetChanged()
                    }

                    is ResultState.Error -> {
                        showLoading(false)
                        showToast(result.error)
                    }
                }
            }
        }
    }

    private fun populateListChat(test: ChatResponse) {
        test.chat.forEach { chatItem ->
            if (chatItem.question.isNotEmpty()) {
                addBotChat(chatItem.question)
            }

            if (chatItem.answer.isNotEmpty()) {
                addUserChat(chatItem.answer)
            }
        }
    }

    private fun addUserChat(message: String) {
        val newUserChat = ListChat.UserChat("Username", message)
        listChat.add(newUserChat)
    }

    private fun addBotChat(message: String) {
        val newUserChat = ListChat.BotChat(message)
        listChat.add(newUserChat)
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        const val INTERVIEW_STATUS = "interview_Status"
    }
}