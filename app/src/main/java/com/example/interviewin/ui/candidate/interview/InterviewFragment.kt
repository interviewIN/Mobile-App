package com.example.interviewin.ui.candidate.interview

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.interviewin.R
import com.example.interviewin.data.ResultState
import com.example.interviewin.data.api.response.InterviewsItem
import com.example.interviewin.data.api.response.JobsItem
import com.example.interviewin.databinding.FragmentInterview2Binding
import com.example.interviewin.factory.ViewModelFactory
import com.example.interviewin.ui.candidate.chat.ChatActivity
import com.example.interviewin.ui.candidate.interview.Interview.Companion.ID
import com.example.interviewin.ui.candidate.interview.Interview.Companion.INTERVIEW_COMPANY
import com.example.interviewin.ui.candidate.interview.Interview.Companion.INTERVIEW_STATUS
import com.example.interviewin.ui.candidate.interview.Interview.Companion.INTERVIEW_TITLE
import com.example.interviewin.ui.recruiter.ui.dashboard.RecruiterDashboardAdapter

class InterviewFragment : Fragment() {

    private var _binding: FragmentInterview2Binding? = null
    private val binding get() = _binding!!

    private val interviewViewModel by viewModels<InterviewViewModel> {
        ViewModelFactory.getInstance(requireActivity())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentInterview2Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        displayInterview()
    }

    private fun displayInterview() {
        interviewViewModel.getInterviews().observe(viewLifecycleOwner) { result ->
            if (result != null) {
                when (result) {
                    is ResultState.Loading -> {
                        showLoading(true)
                    }

                    is ResultState.Success -> {
                        showLoading(false)
                        val interview = result.data.interviews
                        setUpRecycler(interview)
                    }

                    is ResultState.Error -> {
                        showLoading(false)
                        showToast(result.error)
                    }
                }
            }
        }
    }

    private fun setUpRecycler(interview: List<InterviewsItem>) {
        binding.rvCandidateJobs.layoutManager = LinearLayoutManager(requireContext())
        val adapter = InterviewAdapter(
            onItemClick = {
                showDecisionDialog(it)
            }
        )

        adapter.submitList(interview)
        binding.rvCandidateJobs.adapter = adapter
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE

    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private fun showDecisionDialog(interview: InterviewsItem) {
        val dialog = AlertDialog.Builder(requireContext())
        dialog.setTitle("Candidate Decision")
            .setMessage("Do you want to start the interview for this job?")
            .setCancelable(true)
            .setPositiveButton("Start Interview") { _, _ ->
                val intent = Intent(requireContext(), ChatActivity::class.java)
                intent.putExtra(ChatActivity.INTERVIEW_ID, interview.id)
                intent.putExtra(ChatActivity.INTERVIEW_STATUS, interview.status)
                startActivity(intent)
            }
            .setNegativeButton("Cancel") { dialogInterface, _ ->
                dialogInterface.cancel()
            }
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}