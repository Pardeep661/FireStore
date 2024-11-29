package com.pardeep.firestore

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(
    var itemArray: ArrayList<ItemData>,
    var recyclerInterface: RecyclerInterface
) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {
    class ViewHolder(var view : View) : RecyclerView.ViewHolder(view) {
        var imageView = view.findViewById<ImageView>(R.id.imageView)
        var nameTv = view.findViewById<TextView>(R.id.nameTv)
        var rollNoTv = view.findViewById<TextView>(R.id.rollNoTv)
        var updateBtn = view.findViewById<Button>(R.id.updateBtn)
        var deleteBtn = view.findViewById<Button>(R.id.deleteBtn)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_recycler_view,parent,false))
    }

    override fun getItemCount(): Int {
        return itemArray.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.imageView.setImageResource(itemArray[position].image!!)
        holder.nameTv.setText(itemArray[position].name)
        holder.rollNoTv.setText(itemArray[position].rollNo.toString())
        holder.deleteBtn.setOnClickListener {
            recyclerInterface.delete(position)
        }

        holder.updateBtn.setOnClickListener {
            recyclerInterface.update(position)
        }

    }

}
