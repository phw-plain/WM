package com.example.wm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import com.example.weatherdustchecker.WeatherPageFragment

class WheatherMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        val fragment = WeatherPageFragment.newInstance(37.579876, 126.976998)

        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.fragment_container, fragment)
        transaction.commit()

        val LearnButton = findViewById<ImageButton>(R.id.learn)
        LearnButton.setOnClickListener {
            val intent = Intent(this, LearningActivity::class.java)
            startActivity(intent)
        }
    }
}