package com.example.reading_file

import java.io.File

fun main()
{
    val medalArray=ArrayList<Madllist>()
var total:Int=0
    val list= arrayListOf<Int>()
   File("C:\\Users\\lysie\\AndroidStudioProjects\\reading_file\\app\\src\\main\\java\\com\\example\\reading_file\\medallists.csv")
       .forEachLine {
           val medallist=Madllist()
           val temp=it.split(",")
           medallist.country=temp[0] //country
           medallist.ioc=temp[1]//ioc code
           medallist.timeCom=temp[2].toInt()
           medallist.gold=temp[3].toInt()
           medallist.silver=temp[4].toInt()
           medallist.bronze=temp[5].toInt()
           medalArray.add(medallist)
           total=medallist.gold+medallist.silver+medallist.bronze
           list.add(total)


       }

    list.sortDescending()
    println(list)
}