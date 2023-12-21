package com.example.interviewin.ui.recruiter.ui.appliedlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.interviewin.R
import com.example.interviewin.data.api.response.JobsItem
import com.example.interviewin.databinding.ActivityAppliedListBinding

class AppliedListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAppliedListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAppliedListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val idJob = intent.getIntExtra(ID_JOB, 1)

    }

    private fun showDecisionDialog(candidate: JobsItem) {
        val dialog = AlertDialog.Builder(this)
        dialog.setTitle("Candidate Decision")
            .setMessage("Do you want to accept or reject the candidate for the position of ${candidate.title}?")
            .setCancelable(true)
            .setPositiveButton("Accept") { _, _ ->
                showToast("Candidate for ${candidate.title} accepted (ID: ${candidate.id})")
                // Perform actions for accepting the candidate
            }
            .setNegativeButton("Reject") { _, _ ->
                showToast("Candidate for ${candidate.title} rejected (ID: ${candidate.id})")
                // Perform actions for rejecting the candidate
            }
            .show()
    }


    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        const val ID_JOB = "id_job"
    }
}