package com.lucassimao.listadetarefas.ui.update

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.button.MaterialButton
import com.lucassimao.listadetarefas.data.model.TaskModel
import com.lucassimao.listadetarefas.databinding.FragmentUpdateTaskBinding
import com.lucassimao.listadetarefas.ui.TaskViewModel
import com.lucassimao.listadetarefas.ui.home.HomeFragment
import com.lucassimao.listadetarefas.utils.enableButtonWhenEditTextHasText
import org.koin.androidx.viewmodel.ext.android.viewModel

class UpdateTaskFragment : Fragment() {
    private lateinit var binding: FragmentUpdateTaskBinding
    private val viewModel by viewModel<TaskViewModel>()
    private var editTextNameTask: EditText? = null
    private var buttonUpdateTask: MaterialButton? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUpdateTaskBinding.inflate(inflater, container, false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        editTextNameTask = binding.contentUpdateTask.tilTaskTitle.editText
        buttonUpdateTask = binding.contentUpdateTask.btnSaveTask

        setupClickListeners()
        checkBundleNote()
        enableButtonWhenEditTextHasText(editTextNameTask, buttonUpdateTask)

    }

    private fun setupClickListeners() {
        buttonUpdateTask!!.setOnClickListener {
            updateTask()
            closeScreen()
        }
        binding.contentUpdateTask.btnCancelTask.setOnClickListener {
            closeScreen()
        }
    }

    private fun checkBundleNote() {
        val bundle = arguments?.getParcelable<TaskModel>(HomeFragment.KEY)

        bundle?.let { task ->
            editTextNameTask!!.setText(task.name_task)
        }
    }

    private fun updateTask() {
        val bundle = arguments?.getParcelable<TaskModel>(HomeFragment.KEY)

        bundle?.let { task ->
            val nameTask = editTextNameTask!!.text.toString()
            val updatedTask = TaskModel(task.id, nameTask, task.check_task)
            viewModel.updateTask(updatedTask)
        }
    }

    private fun closeScreen() {
        findNavController().popBackStack()
    }

}