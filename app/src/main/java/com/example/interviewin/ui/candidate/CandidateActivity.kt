package com.example.interviewin.ui.candidate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.interviewin.R
import com.example.interviewin.databinding.ActivityCandidateBinding

class CandidateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCandidateBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCandidateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        replaceFragment(Dashboard())


        binding.bottomNavigationView.setOnItemSelectedListener {

            when(it.itemId){

                R.id.dashboard -> replaceFragment(Dashboard())
                R.id.interview -> replaceFragment(Interview())
                R.id.profile -> replaceFragment(Profile())

                else ->{

                }

            }

            true

        }


    }

    private fun replaceFragment(fragment : Fragment){

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout,fragment)
        fragmentTransaction.commit()


    }
}