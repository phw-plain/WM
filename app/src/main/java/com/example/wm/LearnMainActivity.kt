package com.example.wm

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView

class LearnMainActivity : AppCompatActivity() {
    // private => 이 클래스 내부에서만 사용 (접근 제어자)
    // lateinit => 나중(=생성자에서 말고)에 초기화할 값을 지정하기 위해서 사용하는 키워드
    // lateinit을 쓰는 이유는 null 대입 가능으로 안 만들면서 초기화는 나중에 하려고
    // 액티비티의 생성자를 우리가 직접 호출할 수 없음 (안드로이드 시스템에서 호출)
    // 보통 초기화는 onCreate 생명주기 메소드에서 이루어짐
    private lateinit var learns: MutableList<Learn> // 명언 데이터를 저장할 리스트
    private lateinit var pref:SharedPreferences

    fun initializeQuotes(){
        // 프리퍼런스 객체를 가져오기
        // "quotes.xml" 파일에 명언 데이터 저장
        // 초기화 여부를 조사하기
        val initialized = pref.getBoolean("initialized", false)

        if(!initialized){
            // 초기화 로직을 실행
            Log.d("myapp", "초기화 작업 진행")
            // 명언 5개 추가하기
            Learn.saveToPreference(pref, 0, "와이셔츠 세탁 방법", "이미지 1")
            Learn.saveToPreference(pref, 1, "수건 세탁 방법", "이미지 2")
            Learn.saveToPreference(pref, 2, "니트 세탁 방법", "이미지 3")
            Learn.saveToPreference(pref, 3, "색깔별로 세탁 하기", "이미지 4")
            Learn.saveToPreference(pref, 4, "볼펜 자국 지우는 방법",  "이미지 5")

            // initialized 값을 true로 바꿔야 함
            val editor = pref.edit()
            editor.putBoolean("initialized", true)
            editor.apply()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.learn_main_activity)

        // setContentView, 뷰가 생성된 후에 ↓를 해야한다.
        val quoteText  = findViewById<TextView>(R.id.quote_text)
        val quoteFrom = findViewById<TextView>(R.id.quote_from)

        pref = getSharedPreferences("quotes", Context.MODE_PRIVATE)

        initializeQuotes()

        learns = Learn.getQuotesFromPreference(pref)

        // Q) "명언 보기" <= 이 버튼 눌렀을 때 다음 코드 실행되도록 고치기
        val listbtn = findViewById<Button>(R.id.quote_list_btn)

        listbtn.setOnClickListener {
            // 액티비티 클래스가 Context 클래스를 상속받기 때문에 Context 객체가 필요한 시점에
            // 액티비티 객체를 전달해도 무방함
            // 목적지( QuoteListActivity)가 있는 명시적 인텐트를 생성
            val intent = Intent(this, LearnListActivity::class.java)
            // 필요하면 데이터를 추가 가능
            intent.putExtra("data1", 100)
            intent.putExtra("data1", "Hello")

            startActivity(intent)
        }


    }
}