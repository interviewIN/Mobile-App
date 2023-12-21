package com.example.interviewin.ui.candidate.interview

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.SearchView
import androidx.fragment.app.Fragment
import com.example.interviewin.R
import com.example.interviewin.data.api.response.JobsItem
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.example.interviewin.databinding.FragmentInterviewBinding
import com.example.interviewin.databinding.SheetLayoutBinding
import com.example.interviewin.ui.candidate.chat.ChatActivity
import com.example.interviewin.ui.recruiter.ui.jobdesc.JobDescFragment

class Interview : Fragment() {
    private lateinit var searchView: SearchView
    private lateinit var searchButton: Button

    private lateinit var binding: FragmentInterviewBinding  // Correct binding type
    private lateinit var sheetBinding: SheetLayoutBinding
    private lateinit var dialog: BottomSheetDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInterviewBinding.inflate(inflater, container, false)
        sheetBinding = SheetLayoutBinding.inflate(inflater, container, false)

        // Initialize SearchView and Button
        searchView = binding.searchView
        searchButton = binding.searchButton

        // Set listener for search button click
        searchButton.setOnClickListener {
            // Handle search button click
            val query = searchView.query.toString()
        }

        // Set listener for search query submit
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                performSearch()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // Handle text change if needed
                return true
            }
        })

        return binding.root
    }

    // Method called when search button is clicked or search query is submitted
    private fun performSearch() {
        val query = searchView.query.toString()
        // Perform actions based on the search query
        // For example, navigate to search results or update a RecyclerView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dialog = BottomSheetDialog(requireContext())
        dialog.setContentView(sheetBinding.root)


//        dialog.window?.attributes?.windowAnimations = R.style.DialogAnimation
        binding.searchButton.visibility = View.GONE

        binding.searchButton.setOnClickListener {
//            dialog.show()
//            showBottomSheetDialog()
        }

        sheetBinding.btnSheet.setOnClickListener {
            val text = sheetBinding.edtSheet.text.toString()
//            binding.textView.text = text
            dialog.dismiss()
        }

        val id = arguments?.getString(ID)
        binding.tvJob.text = arguments?.getString(INTERVIEW_TITLE)
        val status = arguments?.getString(INTERVIEW_STATUS)
        binding.tvTime.text = "Interview Status ($status)"
        binding.tvCompany.text = arguments?.getString(INTERVIEW_COMPANY)

        binding.startIN.setOnClickListener {
            val intent = Intent(requireContext(), ChatActivity::class.java)
            intent.putExtra(ChatActivity.INTERVIEW_ID, id)
            intent.putExtra(ChatActivity.INTERVIEW_STATUS, status)
        }
    }

    companion object{
        const val ID = "id"
        const val INTERVIEW_TITLE = "title"
        const val INTERVIEW_STATUS = "status"
        const val INTERVIEW_COMPANY = "company"
    }
}
