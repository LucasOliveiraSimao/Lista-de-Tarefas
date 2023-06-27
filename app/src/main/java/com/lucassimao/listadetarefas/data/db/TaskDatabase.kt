package com.lucassimao.listadetarefas.data.db

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.DeleteColumn
import androidx.room.RenameColumn
import androidx.room.RoomDatabase
import androidx.room.migration.AutoMigrationSpec
import com.lucassimao.listadetarefas.data.model.TaskModel

@Database(
    entities = [TaskModel::class],
    version = 4,
    autoMigrations = [
        AutoMigration(from = 1, to = 2),
        AutoMigration(from = 2, to = 3, spec = TaskDatabase.Migration2To3::class),
        AutoMigration(from = 3, to = 4, spec = TaskDatabase.Migration3To4::class)
    ]
)
abstract class TaskDatabase : RoomDatabase() {
    abstract val taskDAO: TaskDAO

    @DeleteColumn(tableName = "note_table", columnName = "note_desc")
    @DeleteColumn(tableName = "note_table", columnName = "note_date")
    @DeleteColumn(tableName = "note_table", columnName = "note_hour")
    @DeleteColumn(tableName = "note_table", columnName = "note_color")
    class Migration2To3 : AutoMigrationSpec

    @RenameColumn(tableName = "note_table", fromColumnName = "note_id", toColumnName = "id")
    @RenameColumn(tableName = "note_table", fromColumnName = "note_title", toColumnName = "name_task")
    class Migration3To4 : AutoMigrationSpec
}