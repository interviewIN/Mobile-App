package com.example.interviewin.ui.auth.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import com.example.interviewin.R
import com.example.interviewin.data.ResultState
import com.example.interviewin.data.model.RegisterRequest
import com.example.interviewin.databinding.ActivityRegisterBinding
import com.example.interviewin.factory.ViewModelFactory
import com.example.interviewin.ui.MainActivity
import com.example.interviewin.ui.auth.login.LoginActivity


class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private val registerViewModel by viewModels<RegisterViewModel> {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val roles = resources.getStringArray(R.array.roles)
        val arrayAdapter = ArrayAdapter(this, R.layout.roles_item, roles)

        binding.apply {
            btnRegister.setOnClickListener {
                register()
            }

            tvHaveAccount.setOnClickListener {
               loginIntent()
            }

            tvLoginNow.setOnClickListener {
                loginIntent()
            }

            registerBackButton.setOnClickListener {
                val toMainIntent = Intent(this@RegisterActivity, MainActivity::class.java)
                startActivity(toMainIntent)
                finish()
            }

            registerEdRole.setAdapter(arrayAdapter)
        }
    }

    private fun register() {
        val edUsername = binding.registerEdUsername
        val edEmail = binding.registerEdEmail
        val edRole = binding.registerEdRole
        val edPassword = binding.registerEdPassword
        val edConfirmPassword = binding.registerEdConfirmPassword

        val username = edUsername.text.toString().trim()
        val email = edEmail.text.toString().trim()
        var role = edRole.text.toString().uppercase().trim()
        val password = edPassword.text.toString().trim()
        val confirmPassword = edConfirmPassword.text.toString().trim()

        if (role == PLACEHOLDER) {
            role = "CANDIDATE"
        }

        if (username.isEmpty()) {
            edUsername.error = resources.getString(R.string.username_error)
        } else {
            edUsername.error = null
        }

        if (email.isEmpty()) {
            edEmail.error = resources.getString(R.string.email_error)
        } else {
            edEmail.error = null
        }

        if (password.length < 8) {
            edPassword.setError(resources.getString(R.string.password_error), null)
        } else {
            edPassword.error = null
        }

        if (password != confirmPassword) {
            showToast("Password and Confirm Password do not match.")
        }

        if (password == confirmPassword && edEmail.error == null && edPassword.error == null && edUsername.error == null) {
            val request = RegisterRequest(username, email, password, role)
            registerViewModel.register(request).observe(this) { result ->
                if (result != null) {
                    when (result) {
                        is ResultState.Loading -> {
                            showLoading(true)
                        }
                        is ResultState.Success -> {
                            showLoading(false)
                            showToast("Registration Successful!")
                            loginIntent()
                        }
                        is ResultState.Error -> {
                            showLoading(false)
                            showToast(result.error)
                        }
                    }
                }
            }
        }
    }

    private fun loginIntent() {
        val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE

    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        const val PLACEHOLDER = "PICK YOUR ROLE"
    }
}