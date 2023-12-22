package com.example.interviewin.ui.recruiter.ui.jobdesc

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.interviewin.databinding.FragmentJobDescBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class JobDescFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentJobDescBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentJobDescBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val desc = arguments?.getString(DESCRIPTION)
        binding.tvJobDescription.text = desc
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val DESCRIPTION = "description"
    }
}