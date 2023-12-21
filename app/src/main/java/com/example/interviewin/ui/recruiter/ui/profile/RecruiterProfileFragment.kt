package com.example.interviewin.ui.recruiter.ui.profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.interviewin.data.ResultState
import com.example.interviewin.databinding.FragmentRecruiterProfileBinding
import com.example.interviewin.factory.ViewModelFactory
import com.example.interviewin.ui.MainActivity


class RecruiterProfileFragment : Fragment() {

    private var _binding: FragmentRecruiterProfileBinding? = null
    private val binding get() = _binding!!

    private val profileViewModel by viewModels<RecruiterProfileViewModel> {
        ViewModelFactory.getInstance(requireActivity())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentRecruiterProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvLogout.setOnClickListener {
            profileViewModel.logout()
            val intent = Intent(requireContext(), MainActivity::class.java)
            startActivity(intent)
        }

        profileViewModel.getUser().observe(viewLifecycleOwner) { result ->
            if (result != null) {
                when (result) {
                    is ResultState.Loading -> {
                        showLoading(true)
                    }

                    is ResultState.Success -> {
                        showLoading(false)
                        binding.tvName.text = result.data.user.name
                        binding.tvUsername.text = result.data.user.email
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
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}