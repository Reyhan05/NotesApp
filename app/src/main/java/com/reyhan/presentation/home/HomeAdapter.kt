package com.reyhan.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.reyhan.data.local.Notes
import com.reyhan.noteapp.databinding.RowItemNotesBinding

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.MyViewHolder>() {

    val listNotes = ArrayList<Notes>()

    inner class MyViewHolder(val binding: RowItemNotesBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyViewHolder(
        RowItemNotesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = listNotes[position]
        holder.binding.apply {
            mNotes = data
            executePendingBindings()
        }
    }

    override fun getItemCount() = listNotes.size

    fun setData(data: List<Notes>?) {
        if (data == null) return
        val diffCallback = DiffCallBack(listNotes, data)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        listNotes.clear()
        listNotes.addAll(data)
        diffResult.dispatchUpdatesTo(this)
    }

}