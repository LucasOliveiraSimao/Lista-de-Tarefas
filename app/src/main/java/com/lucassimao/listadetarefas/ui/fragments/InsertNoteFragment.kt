package com.lucassimao.listadetarefas.ui.fragments

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
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

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {
        binding = FragmentInsertNoteBinding.inflate(inflater, container, false)

        insertListenersButtons()
        selectedColorNote()

        binding.btnSaveNote.setOnClickListener {

            if (!isEmptyNoteField(binding.tilNoteTitle.editText)) {

                emptyFieldMessage(context, getString(R.string.title_field_cannot_be_empty)).show()

            } else if (!isEmptyNoteField(binding.tilNoteDesc.editText)) {

                val message = getString(R.string.description_field_cannot_be_empty)
                emptyFieldMessage(context, message).show()

            } else {

                binding.apply {

                    val newNote = setupInsertNote(
                        tilNoteTitle.editText,
                        tilNoteDesc.editText,
                        tilNoteDate.editText,
                        tilNoteHour.editText
                    )

                    viewModel.insertNote(newNote)
                    finishFragment()
                }

            }

        }

        binding.btnCancelNote.setOnClickListener {
            finishFragment()
        }

        return binding.root
    }

    private fun selectedColorNote() {

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
    private fun insertListenersButtons() {

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

    private fun finishFragment() {
        findNavController().popBackStack()
    }

    private fun setupInsertNote(
        title: EditText?,
        description: EditText?,
        date: EditText?,
        hour: EditText?
    ): NoteModel {

        val noteTile = title?.text.toString()
        val noteDesc = description?.text.toString()
        val noteDate = date?.text.toString()
        val noteHour = hour?.text.toString()

        return if (colorNote < 0) {
            NoteModel(0, noteTile, noteDesc, noteDate, noteHour, colorNote)
        } else {
            NoteModel(0, noteTile, noteDesc, noteDate, noteHour, resources.getColor(R.color.white))
        }

    }

}