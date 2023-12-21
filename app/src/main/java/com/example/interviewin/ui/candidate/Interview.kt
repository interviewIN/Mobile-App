package com.example.interviewin.ui.candidate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.SearchView
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.example.interviewin.databinding.FragmentInterviewBinding
import com.example.interviewin.databinding.SheetLayoutBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class Interview : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

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

        binding.searchButton.setOnClickListener {
            dialog.show()
        }

        sheetBinding.btnSheet.setOnClickListener {
            val text = sheetBinding.edtSheet.text.toString()
            binding.textView.text = text
            dialog.dismiss()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Interview().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
