package com.example.weatherdustchecker

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.wm.APICall
import com.example.wm.LearningActivity
import com.example.wm.R
import com.example.wm.WheatherMainActivity
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import java.net.URL

class WeatherPageFragment : Fragment() {
    lateinit var temperatureText: TextView
    lateinit var weatherImage: ImageView
    lateinit var LearnButton: ImageButton

    @JsonIgnoreProperties(ignoreUnknown=true)
    data class OpenWeatherAPIJSONResponse(
        val main: Map<String, String>,
        val weather: List<Map<String, String>>)

    companion object {
        fun newInstance(lat : Double, lon : Double) : WeatherPageFragment {
            val fragment = WeatherPageFragment()

            val args = Bundle()
            args.putDouble("lat", lat)
            args.putDouble("lon", lon)
            fragment.arguments = args

            return fragment
        }
    }

    // onCreateView(뷰를 만들 때) 메소드
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.activity_main,
            container, false)

        temperatureText = view.findViewById<TextView>(R.id.weather_temp_text)
        weatherImage = view.findViewById<ImageView>(R.id.weather_icon)

        LearnButton = view.findViewById<ImageButton>(R.id.learn)
        LearnButton.setOnClickListener {
            val intent = Intent(getActivity(), LearningActivity::class.java)
            startActivity(intent)
        }


        return view
    }

    val appID = "3c2e21debab20d935652b30bca800db9"

    // onViewCreated(뷰가 만들어진 후에) 메소드
    @SuppressLint("UseRequireInsteadOfGet")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val lat = arguments!!.getDouble("lat")
        val lon = arguments!!.getDouble("lon")
        val url =
            "http://api.openweathermap.org/data/2.5/weather?units=metric&appid=${appID}&lat=${lat}&lon=${lon}"

        APICall(object : APICall.APICallback {
            override fun onComplete(result: String) {
                var mapper = jacksonObjectMapper()
                var data = mapper?.readValue<OpenWeatherAPIJSONResponse>(result)

                val temp = data.main.get("temp")
                temperatureText.text = temp
                temperatureText.text = temperatureText.text.split(".")[0]

                val id = data.weather[0].get("id")
                if (id != null) {
                    when {
                        id.startsWith("2") -> {
                            weatherImage.setImageResource(R.drawable.flash)
                            // 천둥, 번개
                        }
                        id.startsWith("3") -> {
                            weatherImage.setImageResource(R.drawable.rain)
                            // 이슬비
                        }
                        id.startsWith("5") -> {
                            weatherImage.setImageResource(R.drawable.rain)
                            // 비
                        }
                        id.startsWith("6") -> {
                            weatherImage.setImageResource(R.drawable.snow)
                            // 눈
                        }
                        id.startsWith("7") -> {
                            weatherImage.setImageResource(R.drawable.cloudy)
                            // 흐림
                        }
                        id.equals("800") -> {
                            weatherImage.setImageResource(R.drawable.sun)
                            // 화창
                        }
                        id.startsWith("8") -> {
                            weatherImage.setImageResource(R.drawable.cloud)
                            // 구름 낌
                        }
                        else -> weatherImage.setImageResource(R.drawable.why)

                    }
                }
            }
        }).execute(URL(url))
    }



}