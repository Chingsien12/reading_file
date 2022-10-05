package com.example.reading_file

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import java.util.prefs.Preferences

class DetailActivity : AppCompatActivity() {
    private lateinit var detail: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        detail=findViewById(R.id.detail)


        val sharedPref = this.getSharedPreferences("myfile",Context.MODE_PRIVATE)
        val country:String? = sharedPref.getString("Country", "no").toString()
        val ioc = sharedPref.getString("IOC", "no").toString()
        detail.text="The last country clicked was $country ($ioc)"
    }
}