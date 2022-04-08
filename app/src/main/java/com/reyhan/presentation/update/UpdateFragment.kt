package com.reyhan.presentation.update

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.reyhan.data.local.Notes
import com.reyhan.noteapp.R
import com.reyhan.noteapp.databinding.FragmentUpdateBinding
import com.reyhan.utils.ExtensionFunction.setActionBar
import com.reyhan.utils.HelperFunction.parseToPriority
import com.reyhan.utils.HelperFunction.setPriorityColor
import java.text.SimpleDateFormat
import java.util.*

class UpdateFragment : Fragment() {

    private var _binding: FragmentUpdateBinding? = null
    private val binding get() = _binding as FragmentUpdateBinding
    private val args by navArgs<UpdateFragmentArgs>()
    private val updateViewModel by viewModels<NotesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUpdateBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)
        binding.updateArgs = args

        binding.apply {
            toolbarUpdate.setActionBar(requireActivity())
            spinnerPrioritiesUpdate.onItemSelectedListener =
                context?.let { setPriorityColor(it, priorityIndicator) }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.menu_save, menu)
        val item = menu.findItem(R.id.menu_save)
        item.actionView.findViewById<AppCompatImageButton>(R.id.btn_save).setOnClickListener {
            updateData()
        }
    }

    private fun updateData() {
        binding.apply {
            val title = edtTitleUpdate.text.toString()
            val desc = edtDescriptionUpdate.text.toString()
            val priority = spinnerPrioritiesUpdate.selectedItem.toString()

            val date = Calendar.getInstance().time
            val formattedDate = SimpleDateFormat("ddd-MM-yyyy", Locale.getDefault()).format(date)
            val currentData =
                Notes(args.notes.id, title, desc, formattedDate, parseToPriority(context, priority))
            updateViewModel.updateNote(currentData)
            val action = UpdateFragmentDirections.actionUpdateFragmentToDetailFragment(currentData)
            findNavController().navigate(action)

        }
        Toast.makeText(context, "Notes Has been Update", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


