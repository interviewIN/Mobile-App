package com.example.dashboard_capstone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnMoveActivity: Button = findViewById(R.id.btn_candidate)
        btnMoveActivity.setOnClickListener(this)

        val btnMoveDataActivity: Button = findViewById(R.id.btn_recruiter)
        btnMoveDataActivity.setOnClickListener(this)


 }
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_candidate -> {
                val moveIntent = Intent(this@MainActivity, MoveCandidate::class.java)
                startActivity(moveIntent)
            }

            R.id.btn_recruiter -> {
                val moveIntent = Intent(this@MainActivity, MoveRecruiter::class.java)
                startActivity(moveIntent)

            }
        }}
}