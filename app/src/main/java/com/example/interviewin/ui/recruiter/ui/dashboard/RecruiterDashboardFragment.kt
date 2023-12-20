package com.example.interviewin.ui.recruiter.ui.dashboard

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
import com.example.interviewin.databinding.FragmentRecruiterDashboardBinding
import com.example.interviewin.factory.ViewModelFactory
import com.example.interviewin.ui.recruiter.ui.jobdesc.JobDescFragment
import com.example.interviewin.ui.recruiter.ui.jobdesc.JobDescFragment.Companion.DESCRIPTION

class RecruiterDashboardFragment : Fragment() {

    private var _binding: FragmentRecruiterDashboardBinding? = null
    private val binding get() = _binding!!

    private val dashboardViewModel by viewModels<RecruiterDashboardViewModel> {
        ViewModelFactory.getInstance(requireActivity())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentRecruiterDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvRecruiterDashboard.layoutManager = LinearLayoutManager(requireContext())
        binding.rvRecruiterDashboard.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                LinearLayoutManager.VERTICAL
            )
        )

        displayJob()
    }

    private fun displayJob() {
        dashboardViewModel.getJobs().observe(viewLifecycleOwner) { result ->
            if (result != null) {
                when (result) {
                    is ResultState.Loading -> {
                        showLoading(true)
                    }

                    is ResultState.Success -> {
                        showLoading(false)
                        val jobList = result.data.jobs
                        setUpRecycler(jobList)
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
        val adapter = RecruiterDashboardAdapter(
            onBtnMoreClick = {
                showBottomSheetDialog(it)
            }
        )

        adapter.submitList(job)
        binding.rvRecruiterDashboard.adapter = adapter
    }

    private fun showBottomSheetDialog(job: JobsItem) {
        val fragment = JobDescFragment()
        val bundle = Bundle()
        bundle.putString(DESCRIPTION, job.description)
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