package com.lucassimao.listadetarefas.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.lucassimao.listadetarefas.R
import com.lucassimao.listadetarefas.data.model.TaskModel
import com.lucassimao.listadetarefas.databinding.FragmentHomeBinding
import com.lucassimao.listadetarefas.ui.TaskViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel by viewModel<TaskViewModel>()
    private lateinit var adapter: TaskAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        setupRecyclerView()
        setupFabClickListener()

        return binding.root
    }

    private fun setupFabClickListener() {
        binding.fabInsertTask.setOnClickListener {
            navigateToInsertTaskFragment()
        }
    }

    private fun navigateToInsertTaskFragment() {
        findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToInsertNoteFragment())
    }

    private fun setupRecyclerView() {
        adapter = TaskAdapter(object : OnItemClickListener {
            override fun onItemClick(task: TaskModel, isChecked: Boolean) {
                val updateTask = TaskModel(task.id, task.name_task, isChecked)
                viewModel.updateTask(updateTask)
            }
        })
        binding.rvListTasks.adapter = adapter
        observeTasks()
        setupTaskActions()
    }

    private fun observeTasks() {
        viewModel.listAllTasks.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    private fun setupTaskActions() {
        adapter.updateTask = { task ->
            val bundle = Bundle().apply {
                putParcelable(KEY, task)
            }
            findNavController().navigate(
                R.id.action_homeFragment_to_updateTaskFragment, bundle
            )
        }

        adapter.deleteTask = { task ->
            viewModel.deleteTask(task)
        }
    }

    companion object {
        const val KEY = "key"
    }

}