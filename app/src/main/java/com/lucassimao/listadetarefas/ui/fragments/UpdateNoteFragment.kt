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
import com.lucassimao.listadetarefas.databinding.FragmentUpdateNoteBinding
import com.lucassimao.listadetarefas.ui.NoteViewModel
import com.lucassimao.listadetarefas.utils.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class UpdateNoteFragment : Fragment() {
    private lateinit var binding: FragmentUpdateNoteBinding
    private val viewModel by viewModel<NoteViewModel>()
    private var colorNote: Int = 0

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUpdateNoteBinding.inflate(inflater, container, false)

        val bundle = arguments?.getParcelable<NoteModel>(HomeFragment.KEY)
        checkBundleNote(bundle)

        insertListenersButtons()
        selectedColorNote()

        binding.btnUpdateSaveNote.setOnClickListener {

            if (!isEmptyNoteField(binding.tilUpdateNoteTitle.editText)) {
                emptyFieldMessage(context, getString(R.string.title_field_cannot_be_empty)).show()
            } else if (!isEmptyNoteField(binding.tilUpdateNoteDesc.editText)) {

                val message = getString(R.string.description_field_cannot_be_empty)
                emptyFieldMessage(context, message).show()

            } else {
                updateNote(bundle)
                finishFragment()
            }

        }

        binding.btnUpdateCancelNote.setOnClickListener {
            finishFragment()
        }

        return binding.root

    }

    private fun selectedColorNote() {
        binding.apply {
            ivColorRed.setOnClickListener {
                colorNote = context?.resources?.getColor(R.color.red_500)!!
                containerUpdateNote.setBackgroundColor(colorNote)
            }
            ivColorPink.setOnClickListener {
                colorNote = context?.resources?.getColor(R.color.pink_500)!!
                containerUpdateNote.setBackgroundColor(colorNote)
            }
            ivColorDeepPurple.setOnClickListener {
                colorNote = context?.resources?.getColor(R.color.deep_purple_500)!!
                containerUpdateNote.setBackgroundColor(colorNote)
            }
            ivColorGreen.setOnClickListener {
                colorNote = context?.resources?.getColor(R.color.green_500)!!
                containerUpdateNote.setBackgroundColor(colorNote)
            }
            ivColorYellow.setOnClickListener {
                colorNote = context?.resources?.getColor(R.color.yellow_500)!!
                containerUpdateNote.setBackgroundColor(colorNote)
            }
            ivColorOrange.setOnClickListener {
                colorNote = context?.resources?.getColor(R.color.orange_500)!!
                containerUpdateNote.setBackgroundColor(colorNote)
            }
            ivColorWhite.setOnClickListener {
                colorNote = context?.resources?.getColor(R.color.white)!!
                containerUpdateNote.setBackgroundColor(colorNote)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun insertListenersButtons() {
        binding.apply {

            tilUpdateNoteDate.editText?.setOnClickListener {
                setupDate()
            }

            tilUpdateNoteHour.editText?.setOnClickListener {
                setupHour()
            }

        }
    }

    @SuppressLint("SetTextI18n")
    private fun FragmentUpdateNoteBinding.setupHour() {
        val timePicker = MaterialTimePicker.Builder()
            .setTimeFormat(TimeFormat.CLOCK_24H)
            .build()

        timePicker.addOnPositiveButtonClickListener {
            val minutes =
                if (timePicker.minute in 0..9) "0${timePicker.minute}" else timePicker.minute
            val hour =
                if (timePicker.hour in 0..9) "0${timePicker.hour}" else timePicker.hour

            tilUpdateNoteHour.editText?.setText("$hour:$minutes")
        }

        timePicker.show(parentFragmentManager, null)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun FragmentUpdateNoteBinding.setupDate() {

        val datePicker = DatePickerDialog(requireContext(), R.style.ThemeDatePicker, { _, y, m, d ->
            tilUpdateNoteDate.editText!!.setText(formatDate(d, m, y))
        }, setupYear, setupMonth, setupDay)

        datePicker.show()
    }

    private fun checkBundleNote(bundle: NoteModel?) {
        if (bundle != null) {
            binding.apply {
                tilUpdateNoteTitle.editText?.setText(bundle.note_title)
                tilUpdateNoteDesc.editText?.setText(bundle.note_desc)
                tilUpdateNoteDate.editText?.setText(bundle.note_date)
                tilUpdateNoteHour.editText?.setText(bundle.note_hour)
            }

        }
    }

    private fun updateNote(bundle: NoteModel?) {
        if (bundle != null) {
            binding.apply {
                val note = setupUpdateNote(
                    bundle.note_id,
                    tilUpdateNoteTitle.editText,
                    tilUpdateNoteDesc.editText,
                    tilUpdateNoteDate.editText,
                    tilUpdateNoteHour.editText,
                    bundle.note_color
                )
                viewModel.updateNote(note)
            }

        }
    }

    private fun finishFragment() {
        findNavController().popBackStack()
    }

    private fun setupUpdateNote(
        id: Int,
        title: EditText?,
        description: EditText?,
        date: EditText?,
        hour: EditText?,
        color: Int
    ): NoteModel {
        val noteTile = title?.text.toString()
        val noteDesc = description?.text.toString()
        val noteDate = date?.text.toString()
        val noteHour = hour?.text.toString()

        if (colorNote == 0){
            colorNote = color
        }

        return if (colorNote < 0) {
            NoteModel(id, noteTile, noteDesc, noteDate, noteHour, colorNote)
        } else {
            NoteModel(id, noteTile, noteDesc, noteDate, noteHour, resources.getColor(R.color.white))
        }

    }

}