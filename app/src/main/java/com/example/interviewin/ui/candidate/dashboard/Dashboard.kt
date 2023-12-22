package com.example.interviewin.ui.candidate.dashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.interviewin.data.ResultState
import com.example.interviewin.data.api.response.JobsItem
import com.example.interviewin.data.model.ApplyJobRequest
import com.example.interviewin.databinding.FragmentDashboardBinding
import com.example.interviewin.factory.ViewModelFactory
import com.example.interviewin.ui.recruiter.ui.jobdesc.JobDescFragment


class Dashboard : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!
    private val dashboardViewModel by viewModels<DashboardViewModel> {
        ViewModelFactory.getInstance(requireActivity())
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
                        if (result.data.jobs.isNotEmpty()) {
                            binding.rvCandidateDashboard.visibility = View.VISIBLE
                            binding.imageView.visibility = View.GONE
                            binding.textView3.visibility = View.GONE
                        }
                        setUpRecycler(result.data.jobs)
                    }

                    is ResultState.Error -> {
                        showLoading(false)
                        showToast(result.error)
                    }
                }
            }
        }
    }

    private fun showDecisionDialog(job: JobsItem) {
        val dialog = AlertDialog.Builder(requireContext())
        dialog.setTitle("Candidate Decision")
        dialog.setTitle("Apply to Job")
            .setMessage("Do you want to apply for the position of ${job.title}?")
            .setCancelable(true)
            .setPositiveButton("Apply") { _, _ ->
                applyToJob(job.id)
            }
            .setNegativeButton("Cancel") { dialogInterface, _ ->
                dialogInterface.cancel()
            }
            .show()
    }

    private fun applyToJob(id: Int) {
        val request = ApplyJobRequest(id)
        dashboardViewModel.applyJob(request).observe(viewLifecycleOwner) { result ->
            if (result != null) {
                when (result) {
                    is ResultState.Loading -> {
                        showLoading(true)
                    }

                    is ResultState.Success -> {
                        showLoading(false)
                        showToast(result.data.message)
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
        binding.rvCandidateDashboard.layoutManager = LinearLayoutManager(requireContext())
        binding.rvCandidateDashboard.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                LinearLayoutManager.VERTICAL
            )
        )

        val adapter = DashboardAdapter(
            onBtnMoreClick = {
                showBottomSheetDialog(it)
            },
            onItemClick = {
                showDecisionDialog(it)
            }
        )

        adapter.submitList(job)
        binding.rvCandidateDashboard.adapter = adapter
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