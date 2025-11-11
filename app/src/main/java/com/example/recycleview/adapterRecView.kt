package com.example.recycleview

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import javax.security.auth.callback.Callback

class adapterRecView(private val listWayang: ArrayList<dcWayang>) :RecyclerView
    .Adapter<adapterRecView.ListViewHolder>(){

        private lateinit var onItemClickCallback: OnItemClickCallback

    interface OnItemClickCallback{
        fun onItemClicked(data: dcWayang)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback ){
        this.onItemClickCallback = onItemClickCallback
    }

    class ListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val _namaWayang = view.findViewById<TextView>(R.id.namaWayang)
        val _karakterWayang = view.findViewById<TextView>(R.id.karakterWayang)
        val _deskripsiWayang = view.findViewById<TextView>(R.id.deskripsiWayang)
        val _gambarWayang = view.findViewById<ImageView>(R.id.gambarWayang)

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_recycler,parent,false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listWayang.size
    }

    override fun onBindViewHolder(
        holder: ListViewHolder,
        position: Int
    ) {
        val wayang = listWayang[position]
        holder._namaWayang.setText(wayang.nama)
        holder._karakterWayang.setText(wayang.karakter)
        holder._deskripsiWayang.setText(wayang.deskripsi)
        Log.d("TEST", wayang.foto)
        Picasso.get()
            .load(wayang.foto)
            .resize(100,100)
            .into(holder._gambarWayang)

        holder._gambarWayang.setOnClickListener {
//            Toast.makeText(holder.itemView.context,wayang.nama, Toast.LENGTH_LONG).show()
            onItemClickCallback.onItemClicked(listWayang[holder.adapterPosition])
        }
    }

}