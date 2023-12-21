package com.example.dashboard_capstone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MoveRecruiter : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_move_recruiter)

        val tvRecruiter: TextView = findViewById(R.id.tv_recruiter)
    }
}