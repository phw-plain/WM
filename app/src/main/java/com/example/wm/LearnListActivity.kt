package com.example.wm

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class LearnListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.learn_list_activity)

        // RecyclerView를 사용하기 위한 1번 준비물 => 데이터
        // Q) 명언 데이터 가져오기 (Quote 클래스의 get어쩌구 클래스 메소드 이용)
        val pref = getSharedPreferences("quotes", Context.MODE_PRIVATE)
        val quotes = Learn.getQuotesFromPreference(pref)

        // RecyclerView를 사용하기 위한 2번 준비물 -> 데이터를 표시할 XML 레이아웃
        
        // RecyclerView를 사용하기 위한 3번 준비물 -> 레이아웃 매니저
        val layoutManager = LinearLayoutManager(this)
        // val gridManager = GridLayoutManager(this, 2)

        val adapter = LearnAdapter(quotes)

        val recyclerView = findViewById<RecyclerView>(R.id.quote_list)
        // 모든 요소의 크기가 똑같아서 true를 전달하면 내부적으로 추가적인 최적화를 진행함 
        recyclerView.setHasFixedSize(false)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter

    }
}