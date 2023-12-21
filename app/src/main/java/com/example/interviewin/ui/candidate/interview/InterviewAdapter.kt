package com.example.interviewin.ui.candidate.interview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.interviewin.data.api.response.InterviewsItem
import com.example.interviewin.databinding.ItemGetInterviewBinding

class InterviewAdapter(
    private val onItemClick: (InterviewsItem) -> Unit
): ListAdapter<InterviewsItem, InterviewAdapter.MyViewHolder>(DIFF_CALLBACK) {

    inner class MyViewHolder(val binding: ItemGetInterviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(interview: InterviewsItem) {
            binding.tvJob.text = interview.job.title
            binding.tvTime.text = interview.status
            binding.tvCompany.text = interview.job.company.name

            itemView.setOnClickListener {
                onItemClick(interview)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemGetInterviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val interview = getItem(position)
        holder.bind(interview)
    }


    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<InterviewsItem>() {
            override fun areItemsTheSame(oldItem: InterviewsItem, newItem: InterviewsItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: InterviewsItem, newItem: InterviewsItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}