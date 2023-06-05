package com.lucassimao.listadetarefas.ui.update

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.lucassimao.listadetarefas.R
import com.lucassimao.listadetarefas.data.model.TaskModel
import com.lucassimao.listadetarefas.databinding.FragmentUpdateTaskBinding
import com.lucassimao.listadetarefas.ui.NoteViewModel
import com.lucassimao.listadetarefas.ui.home.HomeFragment
import com.lucassimao.listadetarefas.utils.emptyFieldMessage
import com.lucassimao.listadetarefas.utils.isTaskFieldEmpty
import org.koin.androidx.viewmodel.ext.android.viewModel

class UpdateTaskFragment : Fragment() {
    private lateinit var binding: FragmentUpdateTaskBinding
    private val viewModel by viewModel<NoteViewModel>()
    private var message: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUpdateTaskBinding.inflate(inflater, container, false)

        setupClickListeners()
        checkBundleNote()

        return binding.root

    }

    private fun setupClickListeners() {
        binding.contentUpdateTask.btnSaveTask.setOnClickListener {
            val editText = binding.contentUpdateTask.tilTaskTitle.editText

            if (isTaskFieldEmpty(editText)) {
                message = getString(R.string.task_field_cannot_be_empty)
                showEmptyFieldMessage(message)
            } else {
                updateTask()
                closeScreen()
            }

            binding.contentUpdateTask.btnCancelTask.setOnClickListener {
                closeScreen()
            }

        }
    }

    private fun checkBundleNote() {
        val bundle = arguments?.getParcelable<TaskModel>(HomeFragment.KEY)

        bundle?.let { note ->
            binding.contentUpdateTask.tilTaskTitle.editText?.setText(note.note_title)
        }
    }

    private fun updateTask() {
        val bundle = arguments?.getParcelable<TaskModel>(HomeFragment.KEY)

        bundle?.let { note ->
            val id = note.note_id
            val task = binding.contentUpdateTask.tilTaskTitle.editText?.text.toString()
            val updatedTask = TaskModel(id, task,"","","",0)
            viewModel.updateNote(updatedTask)
        }
    }

    private fun showEmptyFieldMessage(message: String) {
        emptyFieldMessage(requireContext(), message).show()
    }

    private fun closeScreen() {
        findNavController().popBackStack()
    }

}