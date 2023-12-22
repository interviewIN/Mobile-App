package com.example.interviewin.ui.recruiter.ui.summary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.example.interviewin.data.ResultState
import com.example.interviewin.databinding.ActivitySummaryBinding
import com.example.interviewin.factory.ViewModelFactory

class SummaryActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySummaryBinding
    private val summaryViewModel by viewModels<SummaryViewModel> {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySummaryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent.getIntExtra(INT_ID, 0)

        summaryViewModel.getInterviewById(id).observe(this) { result ->
            if (result != null) {
                when (result) {
                    is ResultState.Loading -> {
                        showLoading(true)
                    }

                    is ResultState.Success -> {
                        showLoading(false)
                        val summary = result.data.interview.summary
                        binding.tvOverall.text = summary.overallImpression
                        binding.tvChanceGet.text = summary.chanceOfGettingTheJob
                        binding.tvMostRelevant.text = summary.mostRelevantPosition
                        binding.tvPersonal.text = summary.personalCapability
                        binding.tvPsychological.text = summary.psychologicalCapability
                        binding.tvTechnical.text = summary.technicalCapability
                        binding.tvFinal.text = summary.finalThoughts
                    }

                    is ResultState.Error -> {
                        showLoading(false)
                        showToast(result.error)
                    }
                }
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        const val INT_ID = "int_id"
    }
}