package com.example.recycleview

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.squareup.picasso.Picasso

class detWayang : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
         lateinit var _detFotoWayang: ImageView
         lateinit var _detNamaWayang: TextView
         lateinit var _detDetailWayang: TextView

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_det_wayang)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets


        }
        _detFotoWayang   = findViewById(R.id.detFotoWayang)
        _detNamaWayang   = findViewById(R.id.detNamaWayang)
        _detDetailWayang = findViewById(R.id.detDetailWayang)

        val dataIntent = intent.getParcelableExtra<dcWayang>("kirimData", dcWayang::class.java)

        if (dataIntent != null) {
            Picasso.get()
                .load(dataIntent.foto)
                .into(_detFotoWayang)

            _detNamaWayang.text = dataIntent.nama
            _detDetailWayang.text = dataIntent.deskripsi
        }
    }
}