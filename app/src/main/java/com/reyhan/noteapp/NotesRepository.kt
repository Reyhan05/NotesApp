package com.reyhan.noteapp

import androidx.lifecycle.LiveData
import com.reyhan.data.local.Notes
import com.reyhan.data.local.room.NoteDao

class NotesRepository(private val notesDao: NoteDao) {

    val getAllNotes: LiveData<List<Notes>> = notesDao.getAllNotes()
    val sortByHighPriority: LiveData<List<Notes>> = notesDao.sortByHighPriority()
    val sortByLowPriority: LiveData<List<Notes>> = notesDao.sortByLowPriority()

    suspend fun insertNotes(notes: Notes) {
        notesDao.addNote(notes)
    }

    fun searchNoteByQuery(query: String): LiveData<List<Notes>> {
        return notesDao.searchNoteByQuery(query)
    }

    suspend fun deleteAllData() {
        return notesDao.deleteAllData()
    }

    suspend fun deleteNote(notes: Notes) {
        return notesDao.deleteNote(notes)
    }

    suspend fun updateNote(notes: Notes) {
        return notesDao.updateNote(notes)
    }
}