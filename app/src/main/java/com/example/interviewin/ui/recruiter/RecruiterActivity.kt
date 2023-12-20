package com.example.interviewin.ui.recruiter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.interviewin.R
import com.example.interviewin.databinding.ActivityRecruiterBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class RecruiterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecruiterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecruiterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_recruiter)
        navView.setupWithNavController(navController)
    }
}