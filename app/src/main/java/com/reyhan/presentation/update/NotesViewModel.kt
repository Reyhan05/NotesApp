package com.reyhan.presentation.update

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.reyhan.data.local.Notes
import com.reyhan.data.local.room.NoteDao
import com.reyhan.data.local.room.NotesDB
import com.reyhan.noteapp.NotesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesViewModel(application: Application) : AndroidViewModel(application) {
    private val notesDao: NoteDao = NotesDB.getDatabase(application).NoteDao()
    private val notesRepository: NotesRepository = NotesRepository(notesDao)

    val sortByHighPriority: LiveData<List<Notes>> = notesRepository.sortByHighPriority
    val sortByLowPriority: LiveData<List<Notes>> = notesRepository.sortByLowPriority

    fun getAllNotes(): LiveData<List<Notes>> = notesRepository.getAllNotes

    fun insertNotes(notes: Notes) {
        viewModelScope.launch(Dispatchers.IO) {
            notesRepository.insertNotes(notes)
        }
    }

    fun searchNotesByQuery(query: String): LiveData<List<Notes>> {
        return notesRepository.searchNoteByQuery(query)
    }

    fun deleteAllNote() {
        viewModelScope.launch(Dispatchers.IO) {
            notesRepository.deleteAllData()
        }
    }

    fun deleteNote(notes: Notes) {
        viewModelScope.launch(Dispatchers.IO) {
            notesRepository.deleteNote(notes)
        }
    }

    fun updateNote(notes: Notes) {
        viewModelScope.launch(Dispatchers.IO) {
            notesRepository.updateNote(notes)
        }
    }

}