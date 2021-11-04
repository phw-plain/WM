package com.example.wm

import android.content.SharedPreferences

data class Detailed(var idx: Int, var title: String, var text1: String = "", var text2: String = "") {
    companion object {
        fun saveToPreference(pref: SharedPreferences, idx: Int, title: String, text1: String, text2: String) : Detailed {
            val editor = pref.edit()

            editor.putString("$idx.title", title)
            editor.putString("$idx.text1", text1)
            editor.putString("$idx.text2", text2)

            editor.apply()

            return Detailed(idx, title, text1, text2)
        }

        fun getDetailsFromPreference(pref: SharedPreferences): MutableList<Detailed> {
            val details = mutableListOf<Detailed>()

            for(i in 0 until 20) {
                val detailed_title = pref.getString("$i.title", "")!!
                val hand_text = pref.getString("$i.text1", "")!!
                val machine_text = pref.getString("$i.text2", "")!!

                if(detailed_title.isNotBlank()) {
                    details.add(Detailed(i, detailed_title, hand_text, machine_text))
                }
            }

            return details
        }

    }
}