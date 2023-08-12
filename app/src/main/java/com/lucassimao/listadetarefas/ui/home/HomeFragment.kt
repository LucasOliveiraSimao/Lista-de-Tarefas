package com.lucassimao.listadetarefas.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
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
        setupDeleteAllTasks()

        return binding.root
    }

    private fun setupDeleteAllTasks() {
        binding.btnDeleteAll.setOnClickListener {
            viewModel.deleteAllTasks()
        }
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
        setupAdapter()
        observeTasks()
        updateItem()
        observerClickItemCheckBox()
    }

    private fun setupAdapter() {
        adapter = TaskAdapter()
        binding.rvListTasks.adapter = adapter

        val itemTouchHelper = ItemTouchHelper(setupSwipe())
        itemTouchHelper.attachToRecyclerView(binding.rvListTasks)
    }

    private fun observeTasks() {
        viewModel.listAllTasks.observe(viewLifecycleOwner) { tasks ->
            adapter.submitList(tasks)
        }
    }

    private fun updateItem() {
        adapter.updateTask = { task ->
            val bundle = Bundle().apply {
                putParcelable(KEY, task)
            }
            findNavController().navigate(
                R.id.action_homeFragment_to_updateTaskFragment, bundle
            )
        }
    }

    private fun observerClickItemCheckBox() {
        adapter.clickItemCheckBox = { task: TaskModel, isChecked: Boolean ->
            val updateTask = TaskModel(task.id, task.name_task, isChecked)
            viewModel.updateTask(updateTask)
        }
    }

    private fun setupSwipe(): ItemTouchHelper.SimpleCallback {
        val itemTouchHelperCallback =
            object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val position = viewHolder.adapterPosition
                    val item = adapter.currentList[position]
                    deleteItem(item)
                }

            }

        return itemTouchHelperCallback
    }

    fun deleteItem(deletedItem: TaskModel) {
        viewModel.deleteTask(deletedItem)
    }

    companion object {
        const val KEY = "key"
    }

}