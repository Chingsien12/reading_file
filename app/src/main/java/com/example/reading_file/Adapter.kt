package com.example.reading_file

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout

import androidx.recyclerview.widget.RecyclerView
import java.util.zip.Inflater

class AdapterClass(context: Context,private val medal:ArrayList<Madllist>, private val number:List<Int>,private val listener:(Madllist)->Unit):RecyclerView.Adapter<AdapterClass.ViewHolder>(){
    inner class ViewHolder(private var view:View):RecyclerView.ViewHolder(view) {
        private val country:TextView=view.findViewById(R.id.country)//in the layout custom
        private val goldMedal:TextView=view.findViewById(R.id.goldMedal)
        private val layout:ConstraintLayout=view.findViewById(R.id.layout)
        private var numberArray=number
        fun bind(list:Madllist)
        {
            for (i in 0..9)
            {
                if((list.gold+list.bronze+list.silver)==numberArray[i])
                {
                    layout.setBackgroundColor(Color.GREEN)
                    break
                }else
                {
                    layout.setBackgroundColor(Color.WHITE)
                }
            }

            country.text=list.country
            goldMedal.text=(list.gold+list.bronze+list.silver).toString()
            view.setOnClickListener {

                Toast.makeText(it.context,list.country+" has won "+ list.gold.toString()+ " gold Medals", Toast.LENGTH_SHORT).show()
                listener(list)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view=LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_custom,parent,false) as View
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val medallist:Madllist=medal[position]
        holder.bind(medallist)
    }

    override fun getItemCount(): Int {
        return medal.size
    }


}