package com.example.wm

import android.content.SharedPreferences

data class Learn (var idx: Int,
                  var text: String,
                  var from: String = ""){

    // Quote 클래스에 붙일 클래스 메소드 작성 가능
    companion object{
        // Quote를 SharedPreferences에
        // 추가, 수정, 삭제하고 모든 명언 데이터를 반환해주는 클래스 메소드 정의
        fun saveToPreference(pref: SharedPreferences,
                             idx: Int, text: String, from: String) : Learn { // : Quote는 return 타입이다.  (default: Unit)
            val editor = pref.edit()

            // Q) 키를 "idx.text", "idx.from"으로 하여 (ex: 0.text, 0.from, 1.text, 1.from)
            // 각각 text와 from 값을 SharedPreference에 저장하게 하기

            editor.putString("${idx}.text", text.trim())
            editor.putString("${idx}.from", from.trim())
            editor.apply()

            return Learn(idx, text, from)
        }

        // 저장된 명언들을 반환하는 메소드
        fun getQuotesFromPreference(pref: SharedPreferences) : MutableList<Learn> {
            val quotes = mutableListOf<Learn>()

            // preference에서 값을 꺼내서 quotes에다가 붙여주기
            // 0 .. 20 => 0부터 20까지 반복
            // 0 until 20 => 0부터 19까지 반복
            for(i in 0 until 20){
                // !! => nullable 타입을 non-nullable 타입으로 변환하는 연산자
                val quoteText : String = pref.getString("${i}.text", "")!!
                val quoteFrom = pref.getString("${i}.from", "")!!

                if(quoteText.isNotBlank()){
                    quotes.add(Learn(i, quoteText, quoteFrom))
                }
            }

            return quotes
        }
    }
}