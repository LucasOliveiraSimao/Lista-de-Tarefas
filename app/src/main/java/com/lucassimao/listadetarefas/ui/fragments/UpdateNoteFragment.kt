package com.lucassimao.listadetarefas.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.lucassimao.listadetarefas.R
import com.lucassimao.listadetarefas.data.model.NoteModel
import com.lucassimao.listadetarefas.databinding.FragmentUpdateNoteBinding
import com.lucassimao.listadetarefas.ui.NoteViewModel
import com.lucassimao.listadetarefas.utils.emptyFieldMessage
import com.lucassimao.listadetarefas.utils.isEmptyNoteField
import org.koin.androidx.viewmodel.ext.android.viewModel

class UpdateNoteFragment : Fragment() {
    private lateinit var binding: FragmentUpdateNoteBinding
    private val viewModel by viewModel<NoteViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUpdateNoteBinding.inflate(inflater, container, false)

        val bundle = arguments?.getParcelable<NoteModel>("key")
        checkBundleNote(bundle)

        binding.btnUpdateSaveNote.setOnClickListener {
            if (!isEmptyNoteField(binding.etUpdateNoteTitle)) {
                emptyFieldMessage(context, getString(R.string.title_field_cannot_be_empty)).show()
            } else if (!isEmptyNoteField(binding.etUpdateNoteDesc)) {
                emptyFieldMessage(
                    context,
                    getString(R.string.description_field_cannot_be_empty)
                ).show()
            } else {
//                updateNote(bundle)
            }

            finishFragment()

        }

        return binding.root

    }

    private fun checkBundleNote(bundle: NoteModel?) {
        if (bundle != null) {
            binding.etUpdateNoteTitle.setText(bundle.note_title)
            binding.etUpdateNoteDesc.setText(bundle.note_desc)
        }
    }

//    private fun updateNote(bundle: NoteModel?) {
//        if (bundle != null) {
//            val note = setupUpdateNote(
//                bundle.note_id,
//                binding.etUpdateNoteTitle,
//                binding.etUpdateNoteDesc
//            )
//            viewModel.updateNote(note)
//        }
//    }

    private fun finishFragment() {
        findNavController().popBackStack()
    }

//    private fun setupUpdateNote(
//        noteID: Int,
//        etNoteTitle: AppCompatEditText,
//        etNoteDesc: AppCompatEditText
//    ): NoteModel {
//        val noteTile = etNoteTitle.text.toString()
//        val noteDesc = etNoteDesc.text.toString()
//        return NoteModel(noteID, noteTile, noteDesc)
//    }

}