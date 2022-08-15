package com.lucassimao.listadetarefas.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.lucassimao.listadetarefas.R
import com.lucassimao.listadetarefas.data.model.NoteModel
import com.lucassimao.listadetarefas.databinding.FragmentInsertNoteBinding
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

        binding.btnSaveNote.setOnClickListener {
            if (!isEmptyNoteField(binding.etNoteTitle)) {
                emptyFieldMessage(context, getString(R.string.title_field_cannot_be_empty)).show()
            } else if (!isEmptyNoteField(binding.etNoteDesc)) {
                emptyFieldMessage(context, getString(R.string.description_field_cannot_be_empty)).show()
            } else {
                val note = setupNote(binding.etNoteTitle, binding.etNoteDesc)
                viewModel.insertNote(note)
            }

        }

        return binding.root
    }

    private fun setupNote(etNoteTitle: EditText, etNoteDesc: EditText): NoteModel {
        val noteTile = etNoteTitle.text.toString()
        val noteDesc = etNoteDesc.text.toString()

        return NoteModel(0, noteTile, noteDesc)
    }
}