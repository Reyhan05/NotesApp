package com.reyhan.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.reyhan.data.local.Notes

@Database(entities = [Notes::class], version = 1, exportSchema = false)
abstract class NotesDB : RoomDatabase() {
    abstract fun NoteDao(): NoteDao

    companion object {
        @Volatile
        var instace: NotesDB? = null

        @JvmStatic
        fun getDatabase(context: Context): NotesDB {
            if (instace == null) {
                synchronized(this) {
                    instace = Room.databaseBuilder(
                        context,
                        NotesDB::class.java,
                        "notes.db"
                    ).build()
                }
            }
            return instace as NotesDB
        }
    }
}