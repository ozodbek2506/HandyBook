package com.example.handybook.API

import android.content.Context
import com.example.handybook.Data.BookData
import com.example.handybook.Data.UserData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class MySharedPreferences private constructor(contexT: Context){
    val sharedPreferences = contexT.getSharedPreferences("data", 0)
    val edit = sharedPreferences.edit()
    val gson = Gson()

    companion object{
        private var instance:MySharedPreferences? = null
        fun newInstance(contexT: Context): MySharedPreferences {
            if (instance == null){
                instance = MySharedPreferences(contexT)
            }
            return instance!!
        }
    }

    fun setLoginData(mutableList: MutableList<UserData>){
        edit.putString("Login", gson.toJson(mutableList)).apply()
    }
    fun getLoginData(): MutableList<UserData>{
        val data: String = sharedPreferences.getString("Login", "")!!
        if (data == ""){
            return mutableListOf()
        }
        val typeToken = object : TypeToken<MutableList<UserData>>(){}.type
        return gson.fromJson(data, typeToken)
    }

    fun GetSelectedBooksList(): MutableList<BookData>{
        val data: String = sharedPreferences.getString("Selected", "")!!
        if (data == ""){
            return mutableListOf()
        }
        val typeToken = object : TypeToken<MutableList<BookData>>(){}.type
        return gson.fromJson(data, typeToken)
    }
    fun SetSelectedBooksList(mutableList: MutableList<BookData>){
        edit.putString("Selected", gson.toJson(mutableList)).apply()
    }
}