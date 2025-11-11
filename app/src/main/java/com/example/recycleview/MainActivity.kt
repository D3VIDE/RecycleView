package com.example.recycleview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

class MainActivity : AppCompatActivity() {
    private lateinit var _nama:Array<String>
    private lateinit var _karakter:Array<String>
    private lateinit var _deskripsi:Array<String>
    private lateinit var _gambar:Array<String>

    private var arWayang = arrayListOf<dcWayang>()
    private lateinit var _tvWayang : RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        _tvWayang = findViewById<RecyclerView>(R.id.tvWayang)
        siapkanData()
        tambahkanData()
        tampilkanData()
    }

    fun siapkanData(){
        _nama = resources.getStringArray(R.array.namaWayang)
        _deskripsi = resources.getStringArray(R.array.deskripsiWayang)
        _karakter = resources.getStringArray(R.array.karakterUtamaWayang)
        _gambar = resources.getStringArray(R.array.gambarWayang)
    }

    fun tambahkanData(){
        for(position in _nama.indices){
            val data = dcWayang(
                _gambar[position],
                _nama[position],
                _karakter[position],
                _deskripsi[position]
            )
            arWayang.add(data)
        }
    }

    fun tampilkanData(){
        _tvWayang.layoutManager = StaggeredGridLayoutManager(2,
            LinearLayoutManager.VERTICAL)
        _tvWayang.adapter = adapterRecView(arWayang)
    }
}