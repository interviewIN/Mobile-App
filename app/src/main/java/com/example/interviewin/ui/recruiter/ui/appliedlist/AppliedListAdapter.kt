package com.example.interviewin.ui.recruiter.ui.appliedlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.interviewin.data.api.response.JobsItem
import com.example.interviewin.databinding.ItemAppiledUserBinding

class AppliedListAdapter(
    private val onItemClick: (JobsItem) -> Unit,
) :
    ListAdapter<JobsItem, AppliedListAdapter.MyViewHolder>(DIFF_CALLBACK) {
    inner class MyViewHolder(val binding: ItemAppiledUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(job: JobsItem) {

            itemView.setOnClickListener {
                onItemClick(job)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemAppiledUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val job = getItem(position)
        holder.bind(job)
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<JobsItem>() {
            override fun areItemsTheSame(oldItem: JobsItem, newItem: JobsItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: JobsItem, newItem: JobsItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}