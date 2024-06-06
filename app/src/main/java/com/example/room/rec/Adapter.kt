package com.example.room.rec

import android.annotation.SuppressLint
import android.text.TextUtils.indexOf
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.room.R
import com.example.room.members.Members
import java.lang.reflect.Member
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.room.Dao
import androidx.room.Database
import com.example.room.members.MemberDao
import com.example.room.members.MembersDatabase
import kotlinx.coroutines.*
import kotlin.collections.indexOfFirst

class Adapter(var list: List<Members>): RecyclerView.Adapter<Adapter.ViewHolder>() {
    private lateinit var Dao: MemberDao
    private lateinit var Database: MembersDatabase
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.name)
        val role: TextView = itemView.findViewById(R.id.role)
        val deleteIcon: ImageView = itemView.findViewById(R.id.dlt)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentMember = list[position]
        holder.apply {
            name.text = currentMember.username
            role.text = currentMember.userrole
            deleteIcon.setOnClickListener {
                // Handle click event for delete icon
                // For example:
                CoroutineScope(Dispatchers.IO).launch {
                    Database = MembersDatabase.getDatabase(itemView.context) // Use itemView.context to get the context
                    Dao = Database.MemberDao()
                    Dao.delete(currentMember)
                    // Update UI on the main thread
                    withContext(Dispatchers.Main) {
                        // Remove the item from the list
                        list = list.toMutableList().apply { removeAt(position) }
                        notifyItemRemoved(position) // Notify adapter about the item being removed
                    }
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}





