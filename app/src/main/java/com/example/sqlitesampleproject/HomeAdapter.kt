package com.example.sqlitesampleproject

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_add_record.view.*
import kotlinx.android.synthetic.main.row_tem.view.*

class HomeAdapter(val context: Context, val recordList: ArrayList<ModelRecord>):RecyclerView.Adapter<HomeAdapter.MyViewHolder>() {
    class MyViewHolder(itemView: View,): RecyclerView.ViewHolder(itemView) {

        fun bind(model: ModelRecord){
            Glide.with(itemView).load(model.image).into(itemView.rowProfileIV)
            itemView.listName.text = model.name
            itemView.listPhone.text = model.phone
            itemView.listDob.text = model.dob
            itemView.listEmail.text = model.email
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.row_tem,parent,false)
        return MyViewHolder(view)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(recordList[position])

        //show record in new activity on clicking item
        holder.itemView.setOnClickListener{
            //need to implement
        }


    }

    override fun getItemCount(): Int = recordList.size
}