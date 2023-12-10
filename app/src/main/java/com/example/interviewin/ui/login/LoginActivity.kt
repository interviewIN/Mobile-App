package com.example.interviewin.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.example.interviewin.R
import com.example.interviewin.data.ResultState
import com.example.interviewin.databinding.ActivityLoginBinding
import com.example.interviewin.factory.ViewModelFactory
import com.example.interviewin.ui.MainActivity
import com.example.interviewin.ui.register.RegisterActivity
import com.example.interviewin.ui.roles.RolesActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val loginViewModel by viewModels<LoginViewModel> {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            btnLogin.setOnClickListener {
                login()
            }

            tvDontHaveAccount.setOnClickListener {
                registerIntent()
            }

            tvRegisterNow.setOnClickListener {
                registerIntent()
            }

            loginBackButton.setOnClickListener {
                val toMainIntent = Intent(this@LoginActivity, MainActivity::class.java)
                startActivity(toMainIntent)
                finish()
            }
        }
    }

    private fun login() {
        val username = binding.loginEdUsername.text.toString().trim()
        val password = binding.loginEdPassword.text.toString().trim()

        loginViewModel.login(username, password).observe(this) { result ->
            if (result != null) {
                when (result) {
                    is ResultState.Loading -> {
                        showLoading(true)
                    }
                    is ResultState.Success -> {
                        showLoading(false)
                        showToast("Login Successful!")
                        loginViewModel.saveSession(result.data.token)
                        Log.d("check token", result.data.token)
                        val intent = Intent(this@LoginActivity, RolesActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                        startActivity(intent)
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

    private fun registerIntent() {
        val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE

    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}