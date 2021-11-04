package com.example.wm

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class LearningActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.learn_list_activity)

        val currentDetailedSize = intent.getIntExtra("details_size", 0)
        Toast.makeText(applicationContext, "현재 ${currentDetailedSize}개의 세탁방법이 저장되어 있습니다.", Toast.LENGTH_SHORT).show()

        val pref = getSharedPreferences("details", Context.MODE_PRIVATE)

        val details = Detailed.getDetailsFromPreference(pref)

        val layoutManager = LinearLayoutManager(this)

        val adapter = DetailedAdapter(details)

        val recyclerView = findViewById<RecyclerView>(R.id.detailed_list)
        recyclerView.setHasFixedSize(false)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter

        /*
        val detailedButton = findViewById<ImageButton>(R.id.detailed)
        detailedButton.setOnClickListener {
            val intent = Intent(this, DetailedActivity::class.java)
            startActivity(intent)
        }
        */
    }
}