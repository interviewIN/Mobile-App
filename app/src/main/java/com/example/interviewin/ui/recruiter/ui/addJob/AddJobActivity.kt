package com.example.interviewin.ui.recruiter.ui.addJob

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.example.interviewin.data.ResultState
import com.example.interviewin.data.model.JobRequest
import com.example.interviewin.databinding.ActivityAddJobBinding
import com.example.interviewin.factory.ViewModelFactory

class AddJobActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddJobBinding
    private val addJobViewModel by viewModels<AddJobViewModel> {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddJobBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val jobTitle = binding.edJobTitle.text
        val jobDescription = binding.edJobDescription.text
        val firstQuestion = binding.edQuestionFirst.text
        val secondQuestion = binding.edQuestionSecond.text
        val thirdQuestion = binding.edQuestionThird.text
        val fourthQuestion = binding.edQuestionFourth.text
        val fifthQuestion = binding.edQuestionFifth.text

        val inputList = listOf(
            jobTitle,
            jobDescription,
            firstQuestion,
            secondQuestion,
            thirdQuestion,
            fourthQuestion,
            fifthQuestion
        )

        binding.tvSave.setOnClickListener {

            if (inputList.any { it!!.isEmpty() }) {
                showToast("Please fill in all fields")
            } else {
                postJob(
                    jobTitle.toString(),
                    jobDescription.toString(),
                    firstQuestion.toString(),
                    secondQuestion.toString(),
                    thirdQuestion.toString(),
                    fourthQuestion.toString(),
                    fifthQuestion.toString()
                )
            }
        }
    }

    private fun postJob(
        title: String,
        desc: String,
        question1: String,
        question2: String,
        question3: String,
        question4: String,
        question5: String,
    ) {
        val question = arrayListOf(
            question1,
            question2,
            question3,
            question4,
            question5
        )
        val request = JobRequest(title, desc, question)
        addJobViewModel.postJob(request).observe(this) { result ->
            if (result != null) {
                when (result) {
                    is ResultState.Loading -> {
                        showLoading(true)
                    }

                    is ResultState.Success -> {
                        showLoading(false)
                        showToast("Post Job Successful!")
                        finish()
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
}