package com.example.wm

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailedMainActivity : AppCompatActivity() {
    private lateinit var details: MutableList<Detailed>
    private lateinit var pref: SharedPreferences

    fun initializeDetails() {
        val initialized = pref.getBoolean("initialized", false)
        if(!initialized) {
            Log.d("myapp", "초기화 작업 진행")
            Detailed.saveToPreference(pref, 0, "와이셔츠 세탁 방법", "와이셔츠 원단에 따라 조금씩 틀리지만 가장 오랫동안 입을 수 있는 제일 좋은 세탁법은 드라이크리닝이며, 물세탁을 할 경우는 반드시 찬물에 손세탁으로 하되 비벼 빨지 말아야 합니다.\n" +
                    "    손세탁시 칼라부분의 심지에 손상이 가지 않도록 주의해야 합니다.\n" +
                    "    물에 담궈놓지 않고 적신 후 바로 세탁해야만 합니다.\n" +
                    "    세제는 반드시 물에 타서 써야 하는데, 그 이유는 와이셔츠에 바로 묻혀서 세탁하게 되면 부분 탈색을 가져와 입지 못하게 되기 때문입니다.\n" +
                    "    세탁 후 기계탈수는 절대 금물이며, 잘 털어서 옷걸이에 걸어서 말리는 것이 좋습니다.", "와이셔츠 원단에 따라 조금씩 틀리지만 가장 오랫동안 입을 수 있는 제일 좋은 세탁법은 드라이크리닝이며, 물세탁을 할 경우는 반드시 찬물에 손세탁으로 하되 비벼 빨지 말아야 합니다.\n" +
                    "    손세탁시 칼라부분의 심지에 손상이 가지 않도록 주의해야 합니다.\n" +
                    "    물에 담궈놓지 않고 적신 후 바로 세탁해야만 합니다.\n" +
                    "    세제는 반드시 물에 타서 써야 하는데, 그 이유는 와이셔츠에 바로 묻혀서 세탁하게 되면 부분 탈색을 가져와 입지 못하게 되기 때문입니다.\n" +
                    "    세탁 후 기계탈수는 절대 금물이며, 잘 털어서 옷걸이에 걸어서 말리는 것이 좋습니다.")
            Detailed.saveToPreference(pref, 1, "수건 세탁 방법", "중성 세제를 사용하며 40도 정도의 따뜻한 물에서 세탁하는 것이 좋아요. ", "드럼세탁기를 사용한다면 물추가 버튼을 눌러 물을 넉넉하게 해 준 뒤 세탁해줘요. 수건에는 섬유유연제를 별도로 사용하지 않으며 삶지 않습니다.")

            val editor = pref.edit()
            editor.putBoolean("initialized", true)
            editor.apply()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed)

        val detailTitle = findViewById<TextView>(R.id.detailed_title)
        val handText = findViewById<TextView>(R.id.handwash_text)
        val machineText = findViewById<TextView>(R.id.machine_text)

        pref = getSharedPreferences("details", Context.MODE_PRIVATE)

        initializeDetails()

        details = Detailed.getDetailsFromPreference(pref)

        val toDetailedListButton = findViewById<Button>(R.id.detailed_list_btn)
        toDetailedListButton.setOnClickListener {
            val intent = Intent(this, LearningActivity::class.java)

            intent.putExtra("details_size", details.size)

            startActivity(intent)
        }


    }

}