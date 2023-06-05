package com.lucassimao.listadetarefas.data.model

import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "note_table")
@Parcelize
data class TaskModel(
    @PrimaryKey(autoGenerate = true)
    var note_id: Int,
    val note_title: String,
    val note_desc: String,
    @ColumnInfo(name = "note_date", defaultValue = "")
    val note_date: String,
    @ColumnInfo(name = "note_hour", defaultValue = "")
    val note_hour: String,
    @ColumnInfo(name = "note_color", defaultValue = "0")
    val note_color: Int
) : Parcelable {

    companion object : DiffUtil.ItemCallback<TaskModel>() {
        override fun areItemsTheSame(oldItem: TaskModel, newItem: TaskModel): Boolean {
            return oldItem.note_id == newItem.note_id
        }

        override fun areContentsTheSame(oldItem: TaskModel, newItem: TaskModel): Boolean {
            return oldItem == newItem
        }
    }

}