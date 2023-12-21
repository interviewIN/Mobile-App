package com.example.interviewin.ui.recruiter.ui.interview

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.interviewin.data.ResultState
import com.example.interviewin.data.api.response.JobsItem
import com.example.interviewin.databinding.FragmentRecruiterInterviewBinding
import com.example.interviewin.factory.ViewModelFactory
import com.example.interviewin.ui.recruiter.ui.appliedlist.AppliedListActivity
import com.example.interviewin.ui.recruiter.ui.appliedlist.AppliedListActivity.Companion.ID_JOB
import com.example.interviewin.ui.recruiter.ui.jobdesc.JobDescFragment

class RecruiterInterviewFragment : Fragment() {

    private var _binding: FragmentRecruiterInterviewBinding? = null
    private val binding get() = _binding!!
    private val interviewViewModel by viewModels<RecruiterInterviewViewModel> {
        ViewModelFactory.getInstance(requireActivity())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentRecruiterInterviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvRecruiterInterview.layoutManager = LinearLayoutManager(requireContext())
        binding.rvRecruiterInterview.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                LinearLayoutManager.VERTICAL
            )
        )

        displayJob()
    }

    private fun displayJob() {
        interviewViewModel.getJobs().observe(viewLifecycleOwner) { result ->
            if (result != null) {
                when (result) {
                    is ResultState.Loading -> {
                        showLoading(true)
                    }

                    is ResultState.Success -> {
                        showLoading(false)
                        interviewViewModel.getSession().observe(viewLifecycleOwner) { user ->
                            val filteredJobList = result.data.jobs.filter {
                                it.companyId == user.id
                            }
                            setUpRecycler(filteredJobList)
                        }
                    }

                    is ResultState.Error -> {
                        showLoading(false)
                        showToast(result.error)
                    }
                }
            }
        }
    }

    private fun setUpRecycler(job: List<JobsItem>) {
        val adapter = RecruiterInterviewAdapter(
            onBtnMoreClick = {
                showBottomSheetDialog(it)
            },

            onItemClick = {
                val intent = Intent(requireContext(), AppliedListActivity::class.java)
                intent.putExtra(ID_JOB, it.id)
                startActivity(intent)
            }
        )

        adapter.submitList(job)
        binding.rvRecruiterInterview.adapter = adapter
    }

    private fun showBottomSheetDialog(job: JobsItem) {
        val fragment = JobDescFragment()
        val bundle = Bundle()
        bundle.putString(JobDescFragment.DESCRIPTION, job.description)
        fragment.arguments = bundle
        fragment.show(childFragmentManager, "job_desc")
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE

    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}