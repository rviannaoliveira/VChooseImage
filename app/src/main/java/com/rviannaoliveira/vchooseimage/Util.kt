package com.rviannaoliveira.vchooseimage

import android.content.Context
import com.google.gson.GsonBuilder
import org.json.JSONArray
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

/**
 * Criado por rodrigo on 15/08/17.
 */

object Util {

    fun loadProfiles(context: Context): List<Places> {
        try {
            val builder = GsonBuilder()
            val gson = builder.create()
            val array = JSONArray(getAssetsToString(context))
            val profileList = (0..array.length() - 1).mapTo(ArrayList<Places>()) { gson.fromJson(array.getString(it), Places::class.java) }
            return profileList
        } catch (e: Exception) {
            e.printStackTrace()
            return ArrayList()
        }

    }

    fun getAssetsToString(context: Context): String {
        val buf = StringBuilder()
        try {
            val json = context.assets.open("places.json")
            val `in` = BufferedReader(InputStreamReader(json, "UTF-8"))
            var str = `in`.readLine()

            while (str != null) {
                buf.append(str)
                str = `in`.readLine()
            }

            `in`.close()

        } catch (e: IOException) {
            e.printStackTrace()
        }

        return buf.toString()
    }
}
