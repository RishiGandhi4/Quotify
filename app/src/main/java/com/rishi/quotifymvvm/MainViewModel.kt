package com.rishi.quotifymvvm

import android.content.Context
import androidx.lifecycle.ViewModel
import com.google.gson.Gson

class MainViewModel(val context : Context) : ViewModel() {
    private var quoteList: Array<Quote> = emptyArray()
    private var index = 0
    
    init {
        quoteList = loadQuoteFromAssest()
    }

    private fun loadQuoteFromAssest(): Array<Quote> {
        val inputStream = context.assets.open("quotes.json")  //we will open the json file
        val size : Int = inputStream.available() //we will check the size of that file
        val buffer = ByteArray(size)  //we will create the byteArray for that
        inputStream.read(buffer)  // here we will read
        inputStream.close()  // hera we will close
        val json = String(buffer,Charsets.UTF_8)  //UTF-8 means all the jason file is of a UTF 8 file format so we have defined the UTF-8 here
        val gson = Gson()
        return  gson.fromJson(json,Array<Quote>::class.java)
    }

    fun getQuote() = quoteList[index]

    fun nextQuote() = quoteList[++index]

    fun previousQuote() = quoteList[--index]

}