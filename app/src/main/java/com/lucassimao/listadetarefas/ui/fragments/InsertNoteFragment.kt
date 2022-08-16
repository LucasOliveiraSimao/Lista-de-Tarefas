package com.lucassimao.listadetarefas.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatEditText
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.lucassimao.listadetarefas.R
import com.lucassimao.listadetarefas.data.model.NoteModel
import com.lucassimao.listadetarefas.databinding.FragmentInsertNoteBinding
import com.lucassimao.listadetarefas.ui.NoteViewModel
import com.lucassimao.listadetarefas.utils.emptyFieldMessage
import com.lucassimao.listadetarefas.utils.isEmptyNoteField
import org.koin.androidx.viewmodel.ext.android.viewModel

class InsertNoteFragment : Fragment() {
    private lateinit var binding: FragmentInsertNoteBinding
    private val viewModel by viewModel<NoteViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {
        binding = FragmentInsertNoteBinding.inflate(inflater, container, false)
        val bundle = arguments?.getParcelable<NoteModel>("key")

        setupUpdateNote(bundle)


        binding.btnSaveNote.setOnClickListener {
            if (!isEmptyNoteField(binding.etNoteTitle)) {
                emptyFieldMessage(context, getString(R.string.title_field_cannot_be_empty)).show()
            } else if (!isEmptyNoteField(binding.etNoteDesc)) {
                emptyFieldMessage(
                    context,
                    getString(R.string.description_field_cannot_be_empty)
                ).show()
            } else {
                checkInsertOrUpdateNote(bundle)
            }

            finishFragment()

        }

        return binding.root
    }

    private fun checkInsertOrUpdateNote(bundle: NoteModel?) {
        if (bundle != null) {
            val note = setupUpdateNote(
                bundle.note_id,
                binding.etNoteTitle,
                binding.etNoteDesc
            )
            viewModel.updateNote(note)
        } else {
            val note = setupInsertNote(binding.etNoteTitle, binding.etNoteDesc)
            viewModel.insertNote(note)
        }
    }

    private fun setupUpdateNote(bundle: NoteModel?) {
        if (bundle != null) {
            binding.etNoteTitle.setText(bundle.note_title)
            binding.etNoteDesc.setText(bundle.note_desc)
        }
    }

    private fun finishFragment() {
        findNavController().popBackStack()
    }

    private fun setupInsertNote(
        etNoteTitle: AppCompatEditText,
        etNoteDesc: AppCompatEditText
    ): NoteModel {
        val noteTile = etNoteTitle.text.toString()
        val noteDesc = etNoteDesc.text.toString()

        return NoteModel(0, noteTile, noteDesc)
    }

    private fun setupUpdateNote(
        noteID: Int,
        etNoteTitle: AppCompatEditText,
        etNoteDesc: AppCompatEditText
    ): NoteModel {
        val noteTile = etNoteTitle.text.toString()
        val noteDesc = etNoteDesc.text.toString()
        return NoteModel(noteID, noteTile, noteDesc)
    }
}