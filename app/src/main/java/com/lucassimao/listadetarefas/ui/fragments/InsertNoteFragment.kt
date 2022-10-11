package com.lucassimao.listadetarefas.ui.fragments

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import com.lucassimao.listadetarefas.R
import com.lucassimao.listadetarefas.data.model.NoteModel
import com.lucassimao.listadetarefas.databinding.FragmentInsertNoteBinding
import com.lucassimao.listadetarefas.ui.NoteViewModel
import com.lucassimao.listadetarefas.utils.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class InsertNoteFragment : Fragment() {
    private lateinit var binding: FragmentInsertNoteBinding
    private val viewModel by viewModel<NoteViewModel>()
    private var colorNote: Int = -1
    private var message = ""

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {
        binding = FragmentInsertNoteBinding.inflate(inflater, container, false)

        setupButtons()
        setupNoteColorPicker()

        binding.btnSaveNote.setOnClickListener {

            if (!isEmptyNoteField(binding.tilNoteTitle.editText)) {

                message = getString(R.string.title_field_cannot_be_empty)
                emptyFieldMessage(context, message).show()

            } else if (!isEmptyNoteField(binding.tilNoteDesc.editText)) {

                message = getString(R.string.description_field_cannot_be_empty)
                emptyFieldMessage(context, message).show()

            } else {

                binding.apply {

                    val title = tilNoteTitle.editText?.text.toString()
                    val desc = tilNoteDesc.editText?.text.toString()
                    val date = tilNoteDate.editText?.text.toString()
                    val hour = tilNoteHour.editText?.text.toString()

                    val newNote = setupInsertNote(title, desc, date, hour)

                    viewModel.insertNote(newNote)
                    closeScreen()
                }

            }

        }

        binding.btnCancelNote.setOnClickListener {
            closeScreen()
        }

        return binding.root
    }

    private fun setupNoteColorPicker() {

        binding.apply {
            ivColorRed.setOnClickListener {
                colorNote = context?.resources?.getColor(R.color.red_500)!!
                containerInsertNote.setBackgroundColor(colorNote)
            }
            ivColorPink.setOnClickListener {
                colorNote = context?.resources?.getColor(R.color.pink_500)!!
                containerInsertNote.setBackgroundColor(colorNote)
            }
            ivColorDeepPurple.setOnClickListener {
                colorNote = context?.resources?.getColor(R.color.deep_purple_500)!!
                containerInsertNote.setBackgroundColor(colorNote)
            }
            ivColorGreen.setOnClickListener {
                colorNote = context?.resources?.getColor(R.color.green_500)!!
                containerInsertNote.setBackgroundColor(colorNote)
            }
            ivColorYellow.setOnClickListener {
                colorNote = context?.resources?.getColor(R.color.yellow_500)!!
                containerInsertNote.setBackgroundColor(colorNote)
            }
            ivColorOrange.setOnClickListener {
                colorNote = context?.resources?.getColor(R.color.orange_500)!!
                containerInsertNote.setBackgroundColor(colorNote)
            }
            ivColorWhite.setOnClickListener {
                colorNote = context?.resources?.getColor(R.color.white)!!
                containerInsertNote.setBackgroundColor(colorNote)
            }
        }

    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.N)
    private fun setupButtons() {

        binding.apply {

            tilNoteDate.editText?.setOnClickListener {
                setupDate()
            }

            tilNoteHour.editText?.setOnClickListener {
                setupHour()
            }

        }

    }

    @SuppressLint("SetTextI18n")
    private fun FragmentInsertNoteBinding.setupHour() {
        val timePicker = MaterialTimePicker.Builder()
            .setTimeFormat(TimeFormat.CLOCK_24H)
            .build()

        timePicker.addOnPositiveButtonClickListener {
            val minutes =
                if (timePicker.minute in 0..9) "0${timePicker.minute}" else timePicker.minute
            val hour =
                if (timePicker.hour in 0..9) "0${timePicker.hour}" else timePicker.hour

            tilNoteHour.editText?.setText("$hour:$minutes")
        }

        timePicker.show(parentFragmentManager, null)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun FragmentInsertNoteBinding.setupDate() {

        val datePicker = DatePickerDialog(requireContext(), R.style.ThemeDatePicker, { _, y, m, d ->
            tilNoteDate.editText!!.setText(formatDate(d, m, y))
        }, setupYear, setupMonth, setupDay)

        datePicker.show()
    }

    private fun closeScreen() {
        findNavController().popBackStack()
    }

    private fun setupInsertNote(
        title: String, description: String, date: String, hour: String
    ): NoteModel {

        return if (colorNote < 0) {
            NoteModel(0, title, description, date, hour, colorNote)
        } else {
            NoteModel(0, title, title, title, title, resources.getColor(R.color.white))
        }

    }

}