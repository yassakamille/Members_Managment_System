package com.example.room.rec

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.room.R

class ViewHolder(item: View) : RecyclerView.ViewHolder(item){
    var name : TextView = item.findViewById(R.id.name)
    var role : TextView = item.findViewById(R.id.role)


}