package com.lucassimao.listadetarefas.ui.insert

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.lucassimao.listadetarefas.R
import com.lucassimao.listadetarefas.data.model.TaskModel
import com.lucassimao.listadetarefas.databinding.FragmentInsertTaskBinding
import com.lucassimao.listadetarefas.ui.NoteViewModel
import com.lucassimao.listadetarefas.utils.emptyFieldMessage
import com.lucassimao.listadetarefas.utils.isTaskFieldEmpty
import org.koin.androidx.viewmodel.ext.android.viewModel

class InsertTaskFragment : Fragment() {
    private lateinit var binding: FragmentInsertTaskBinding
    private val viewModel by viewModel<NoteViewModel>()
    private var message: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInsertTaskBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupSaveButton()
        setupCancelButton()

        binding.contentInsertTask.btnCancelTask.setOnClickListener {
            closeScreen()
        }
    }

    private fun setupSaveButton() {
        binding.contentInsertTask.btnSaveTask.setOnClickListener {
            val editText = binding.contentInsertTask.tilTaskTitle.editText

            if (isTaskFieldEmpty(editText)) {
                message = getString(R.string.task_field_cannot_be_empty)
                showEmptyFieldMessage(message)
            } else {
                val task = editText?.text.toString()
                val newTask = TaskModel(0, task,"","","",0)

                viewModel.insertTask(newTask)

                closeScreen()
            }

        }
    }

    private fun showEmptyFieldMessage(message: String) {
        emptyFieldMessage(requireContext(), message).show()
    }

    private fun setupCancelButton() {
        binding.contentInsertTask.btnCancelTask.setOnClickListener {
            closeScreen()
        }
    }

    private fun closeScreen() {
        findNavController().popBackStack()
    }
}