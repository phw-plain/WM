package com.example.wm

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// RecyclerView를 사용하기 위한 4번, 5번번 준비물 -> 뷰 홀더 클래스 + 어댑터 클래스
class LearnAdapter(private val dataList: List<Learn>)
    : RecyclerView.Adapter<LearnAdapter.QuoteItemViewHolder>()
{
        // 보통 뷰 홀더 클래스는 내부 클래스로 정의
        class QuoteItemViewHolder(val view: View) : RecyclerView.ViewHolder(view){
            lateinit var learn : Learn
            val quoteText = view.findViewById<TextView>(R.id.quote_text)
            val quoteFrom = view.findViewById<TextView>(R.id.quote_from)

            fun bind(q: Learn){
                this.learn = q
                quoteText.text = learn.text
                quoteFrom.text = learn.from
            }

        }

    // onCreateViewHolder 역할 => 뷰 홀더 클래스를 생성해서 반환하는 역할
    // 여기서 전달받는 parent는 RecyclerView
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteItemViewHolder {
        // LayoutInflater 역할 => 레이아웃 XML 파일의 식별자를 전달하면 뷰 객체를 반환
        // 모든 뷰 객체는 context 속성을 가지고 있음
        val inflater = LayoutInflater.from(parent.context)
        // inflaste 메소드 (
        // (첫 번째 인자) 레이아웃 리소스 식별자,
        // (두 번째 인자) 여기서 생성될 뷰 객체가 붙을 부모 뷰,
        // (세 번째 인자) 지감 당장 뷰를 부착할 것인지 여부,
        // )
        // 세 번째 인자는 무조건 false로 해야 함
        // (왜냐하면 직접 붙이는게 아니고 안드로이드 시스템에서 뷰 객체를 붙여줌)
        val view = inflater.inflate(viewType, parent, false)

        return QuoteItemViewHolder(view)
    }

    // onBindViewHolder 역할 => 뷰 홀더 객체를 전달 받아서서 해당 뷰 홀더의 객체의 내용을 적절히
    // 변경시켜주는 역할
    override fun onBindViewHolder(holder: QuoteItemViewHolder, position: Int) {
        Log.d("myapp", "onBindViewHolder ${position}")
        // bind 메소드에다가 Quote 객체를 전달
        holder.bind(dataList[position])
    }

    // getItemCount 역할 => 보여줘야 할 목록 아이템의 개수를 반환하는 역할
    override fun getItemCount() = dataList.size

    // getItemViewType 역힐 => 사용할 레이아웃 리소스의 식별자를 반환하는 역할
    override fun getItemViewType(position: Int): Int = R.layout.learn_list_item
}