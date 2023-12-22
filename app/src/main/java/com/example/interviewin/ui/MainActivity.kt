package com.example.interviewin.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.interviewin.databinding.ActivityMainBinding
import com.example.interviewin.factory.ViewModelFactory
import com.example.interviewin.ui.auth.login.LoginActivity
import com.example.interviewin.ui.auth.register.RegisterActivity
import com.example.interviewin.ui.candidate.CandidateActivity
import com.example.interviewin.ui.recruiter.RecruiterActivity
import com.example.interviewin.utils.CANDIDATE
import com.example.interviewin.utils.COMPANY

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel by viewModels<MainViewModel> {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.startBtnLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.startBtnRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        getSession()
    }

    private fun getSession() {
        mainViewModel.getSession().observe(this) {
            if (it.role == CANDIDATE) {
                val intent = Intent(this, CandidateActivity::class.java)
                startActivity(intent)
                finish()
            }

            if (it.role == COMPANY) {
                val intent = Intent(this, RecruiterActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}