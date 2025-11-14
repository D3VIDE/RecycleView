package com.example.recycleview

import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.edit
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {
    private var _nama: MutableList<String> = emptyList<String>().toMutableList()
    private var _karakter: MutableList<String> = emptyList<String>().toMutableList()
    private var _deskripsi: MutableList<String> = emptyList<String>().toMutableList()
    private var _gambar: MutableList<String> = emptyList<String>().toMutableList()
    private var arWayang = arrayListOf<dcWayang>()

    private lateinit var sp: SharedPreferences
    private lateinit var _tvWayang : RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sp = getSharedPreferences("dataSP", MODE_PRIVATE)
        _tvWayang = findViewById<RecyclerView>(R.id.tvWayang)

        bacaDataDariSP()


        if (arWayang.size == 0) {
            siapkanData()
            tambahkanData()
        } else {
            // Jika ada data di SP, isi ke list untuk operasi hapus
            arWayang.forEach { wayang ->
                _nama.add(wayang.nama)
                _gambar.add(wayang.foto)
                _deskripsi.add(wayang.deskripsi)
                _karakter.add(wayang.karakter)
            }
        }

        tampilkanData()

    }

    private fun bacaDataDariSP() {
        val gson = Gson()
        val json = sp.getString("spWayang", "")

        if (!json.isNullOrEmpty()) {
            val type = com.google.gson.reflect.TypeToken.getParameterized(ArrayList::class.java, dcWayang::class.java).type
            val dataDariSP = gson.fromJson<ArrayList<dcWayang>>(json, type)
            arWayang.clear()
            arWayang.addAll(dataDariSP)
        }
    }

    fun siapkanData(){
        _nama = resources.getStringArray(R.array.namaWayang).toMutableList()
        _deskripsi = resources.getStringArray(R.array.deskripsiWayang).toMutableList()
        _karakter = resources.getStringArray(R.array.karakterUtamaWayang).toMutableList()
        _gambar = resources.getStringArray(R.array.gambarWayang).toMutableList()
    }

    fun tambahkanData(){
        val gson = Gson()
        sp.edit {
            arWayang.clear()

            for (position in _nama.indices) {
                val data = dcWayang(
                    _gambar[position],
                    _nama[position],
                    _karakter[position],
                    _deskripsi[position]
                )
                arWayang.add(data)
            }

            val json = gson.toJson(arWayang)
            putString("spWayang", json)
        }
    }

    fun tampilkanData(){
        _tvWayang.layoutManager = LinearLayoutManager(this)
        val adapterWayang = adapterRecView(arWayang)
        _tvWayang.adapter = adapterWayang

        adapterWayang.setOnItemClickCallback(object : adapterRecView.OnItemClickCallback{
            override fun onItemClicked(data: dcWayang) {
                val intent = Intent(this@MainActivity, detWayang::class.java)
                intent.putExtra("kirimData",data)
                startActivity(intent)
            }

            override fun delData(pos: Int) {
                AlertDialog.Builder(this@MainActivity)
                    .setTitle("Hapus Data")
                    .setMessage("Apakah benar data "+_nama[pos]+" akan dihapus? ")
                    .setPositiveButton(
                        "HAPUS",
                        DialogInterface.OnClickListener{ dialog, which ->
                            _gambar.removeAt(pos)
                            _nama.removeAt(pos)
                            _deskripsi.removeAt(pos)
                            _karakter.removeAt(pos)
                            tambahkanData()
                            tampilkanData()
                        }
                    )
                    .setNegativeButton(
                        "BATAL",
                        DialogInterface.OnClickListener{dialog , which ->
                            Toast.makeText(
                                this@MainActivity,
                                "Data berhasil dihapus",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    ).show()
            }
        })
    }
}