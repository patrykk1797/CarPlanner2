package com.example.carplanner.ui.wydatki

import java.time.format.DateTimeFormatter
import java.util.*

data class Item constructor (var id: Int?, var name:String?, var type: String?, var dsc:String?, var price:Float?, var date: String?) {

     constructor() : this(null,null,null,null,null, null)


     override fun toString(): String {
          return  this.id.toString() + ") " + this.name;
     }
}