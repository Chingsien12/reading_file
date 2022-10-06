package com.example.reading_file

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog

class MainActivity : AppCompatActivity() {
    private lateinit var rv: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val medalArray = ArrayList<Madllist>()
        val list= arrayListOf<Int>()
        var total:Int=0
        rv = findViewById(R.id.rv)
        resources.openRawResource(R.raw.medallists).bufferedReader()
            .forEachLine {
                val medallist = Madllist()
                val temp = it.split(",")
                medallist.country = temp[0]
                medallist.ioc = temp[1]//ioc code
                medallist.timeCom = temp[2].toInt()
                medallist.gold = temp[3].toInt()
                medallist.silver = temp[4].toInt()
                medallist.bronze = temp[5].toInt()
                medalArray.add(medallist)
                total=medallist.gold+medallist.silver+medallist.bronze
                list.add(total)
            }
        list.sortDescending()
        rv.adapter = AdapterClass(this, medalArray,list){showDetail(it)}
        rv.layoutManager = LinearLayoutManager(this)

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_option, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.options -> gotointent()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun gotointent()
    {
        var i=Intent(this,DetailActivity::class.java).apply {
            putExtra("id",1)
        }
        startActivity(i)
    }

    fun showDetail(list:Madllist)
    {
        val country:TextView
        val ioc:TextView
        val gold:TextView
        val silver:TextView
        val bronze:TextView
        val total:TextView
        val close:ImageView
        val sharePref=this.getSharedPreferences("myfile",Context.MODE_PRIVATE)
        with (sharePref.edit()){
            putString("Country",list.country)
            putString("IOC",list.ioc)
            apply()
        }

        val dialog=BottomSheetDialog(this)
        val view= layoutInflater.inflate(R.layout.dialog_layout,null)
        country=view.findViewById(R.id.country)
        ioc=view.findViewById(R.id.ioc)
        gold=view.findViewById(R.id.gold)
        silver=view.findViewById(R.id.silver)
        bronze=view.findViewById(R.id.bronze)
        total=view.findViewById(R.id.totalgold)
        close=view.findViewById(R.id.imageView)
        val sum:Int = list.gold+list.silver+list.bronze
        country.text=list.country
        ioc.text=list.ioc
        gold.text="${list.gold} gold medals"
        silver.text="${list.silver} silver medals"
        bronze.text="${list.bronze} bronze medals"
        total.text="Total gold: $sum"
        dialog.setContentView(view)
        dialog.show()
        close.setOnClickListener{
            dialog.dismiss()
        }

    }
}


