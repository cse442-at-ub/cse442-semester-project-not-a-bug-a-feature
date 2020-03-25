package com.example.tutor4tutor_src
import org.json.JSONObject

object JSONParser {
    fun dic2json(data:Map<Int,String>):String
    {
        var jo = JSONObject()
        for (item in data)
        {
            jo.put(item.key.toString(),item.value)
        }
        return jo.toString(4)
    }
    fun json2dic(str:String):Map<Int,String>
    {
        var jo = JSONObject(str)
        var dic: MutableMap<Int, String> = mutableMapOf()
        for (i in 0..3)
        {
            if (jo.has(i.toString()))
            {
                dic[i] = jo.getString(i.toString())
            }
        }
        return dic
    }

}