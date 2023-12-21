package com.example.interviewin.ui.recruiter.ui.appliedlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.interviewin.data.api.response.Interviews
import com.example.interviewin.databinding.ItemAppiledUserBinding

class AppliedListAdapter(
    private val onItemClick: (Interviews) -> Unit,
    private val onBtnNext: (Interviews) -> Unit,
) :
    ListAdapter<Interviews, AppliedListAdapter.MyViewHolder>(DIFF_CALLBACK) {
    inner class MyViewHolder(val binding: ItemAppiledUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(interview: Interviews) {
            binding.tvUsername.text = interview.candidateId.toString()
            binding.tvScore.text = interview.status

            binding.btnRightArrow.setOnClickListener {
                onBtnNext(interview)
            }

            itemView.setOnClickListener {
                onItemClick(interview)
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
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Interviews>() {
            override fun areItemsTheSame(oldItem: Interviews, newItem: Interviews): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Interviews, newItem: Interviews): Boolean {
                return oldItem == newItem
            }
        }
    }
}