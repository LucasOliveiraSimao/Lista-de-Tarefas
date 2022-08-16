package com.lucassimao.listadetarefas.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.lucassimao.listadetarefas.data.model.NoteModel
import com.lucassimao.listadetarefas.databinding.FragmentHomeBinding
import com.lucassimao.listadetarefas.ui.NoteViewModel
import com.lucassimao.listadetarefas.ui.NotesAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel by viewModel<NoteViewModel>()
    private lateinit var adapter: NotesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        setupRecyclerView()

        binding.fabInsertNote.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToInsertNoteFragment())
        }

        return binding.root
    }

    private fun setupRecyclerView() {
        adapter = NotesAdapter(
            onItemClick = {
                deleteNote(it)
            }
        )

        binding.rvListNotes.adapter = adapter
        viewModel.allNotes.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    private fun deleteNote(it: NoteModel) {
        viewModel.deleteNote(it)
    }

}