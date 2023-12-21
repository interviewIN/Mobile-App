package com.example.interviewin.ui.recruiter.ui.appliedlist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.interviewin.data.ResultState
import com.example.interviewin.data.api.response.Interviews
import com.example.interviewin.data.model.PatchStatusRequest
import com.example.interviewin.databinding.ActivityAppliedListBinding
import com.example.interviewin.factory.ViewModelFactory
import com.example.interviewin.ui.recruiter.ui.summary.SummaryActivity
import com.example.interviewin.ui.recruiter.ui.summary.SummaryActivity.Companion.INT_ID
import com.example.interviewin.utils.ACCEPTED
import com.example.interviewin.utils.REJECTED

class AppliedListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAppliedListBinding
    private val appliedListViewModel by viewModels<AppliedListViewModel> {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAppliedListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val title = intent.getStringExtra(TITLE_JOB)
        binding.appliedTitle.text = title

        val idJob = intent.getIntExtra(ID_JOB, 1)
        displayInterviews(idJob)
    }

    private fun displayInterviews(id: Int) {
        appliedListViewModel.getInterviewByJob(id).observe(this) { result ->
            if (result != null) {
                when (result) {
                    is ResultState.Loading -> {
                        showLoading(true)
                    }

                    is ResultState.Success -> {
                        showLoading(false)
                        setUpRecycler(result.data.interviews)
                    }

                    is ResultState.Error -> {
                        showLoading(false)
                        showToast(result.error)
                    }
                }
            }
        }
    }

    private fun setUpRecycler(interview: List<Interviews>) {
        binding.rvAppliedUser.layoutManager = LinearLayoutManager(this)
        val adapter = AppliedListAdapter(
            onItemClick = {
                val intent = Intent(this, SummaryActivity::class.java)
                intent.putExtra(INT_ID, it.id)
                startActivity(intent)
            },
            onBtnNext = {
                showDecisionDialog(it)
            }
        )

        adapter.submitList(interview)
        binding.rvAppliedUser.adapter = adapter
    }

    private fun patchStatus(request: PatchStatusRequest) {
        appliedListViewModel.patchStatus(request).observe(this) { result ->
            if (result != null) {
                when (result) {
                    is ResultState.Loading -> {
                        showLoading(true)
                    }

                    is ResultState.Success -> {
                        showLoading(false)
                        showToast(result.data.updatedInterview.status)
                    }

                    is ResultState.Error -> {
                        showLoading(false)
                        showToast(result.error)
                    }
                }
            }
        }
    }

    private fun showDecisionDialog(interviews: Interviews) {
        val dialog = AlertDialog.Builder(this)
        dialog.setTitle("Candidate Decision")
            .setMessage("Do you want to accept or reject the candidate?")
            .setCancelable(true)
            .setPositiveButton("Accept") { _, _ ->
                val request = PatchStatusRequest(interviews.id, ACCEPTED)
                patchStatus(request)
            }
            .setNegativeButton("Reject") { _, _ ->
                val request = PatchStatusRequest(interviews.id, REJECTED)
                patchStatus(request)
            }
            .show()
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        const val ID_JOB = "id_job"
        const val TITLE_JOB = "title_job"
    }
}