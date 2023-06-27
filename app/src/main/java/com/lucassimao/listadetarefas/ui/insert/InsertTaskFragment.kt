package com.lucassimao.listadetarefas.ui.insert

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.button.MaterialButton
import com.lucassimao.listadetarefas.data.model.TaskModel
import com.lucassimao.listadetarefas.databinding.FragmentInsertTaskBinding
import com.lucassimao.listadetarefas.ui.TaskViewModel
import com.lucassimao.listadetarefas.utils.enableButtonWhenEditTextHasText
import org.koin.androidx.viewmodel.ext.android.viewModel

class InsertTaskFragment : Fragment() {
    private lateinit var binding: FragmentInsertTaskBinding
    private val viewModel by viewModel<TaskViewModel>()
    private var editTextNameTask: EditText? = null
    private var buttonSaveTask: MaterialButton? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInsertTaskBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        editTextNameTask = binding.contentInsertTask.tilTaskTitle.editText
        buttonSaveTask = binding.contentInsertTask.btnSaveTask

        setupSaveButton()
        setupCancelButton()
        enableButtonWhenEditTextHasText(editTextNameTask, buttonSaveTask)

        binding.contentInsertTask.btnCancelTask.setOnClickListener {
            closeScreen()
        }
    }

    private fun setupSaveButton() {
        buttonSaveTask!!.setOnClickListener {
            val task = editTextNameTask!!.text.toString()
            val newTask = TaskModel(0, task, false)

            viewModel.insertTask(newTask)

            closeScreen()

        }
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