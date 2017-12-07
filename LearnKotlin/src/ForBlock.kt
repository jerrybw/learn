package com.jerry.study

/**
 * Created by 向博文 on 2017/12/6.
 */
fun main(args: Array<String>) {
    for(x in 1..10){
        println(x)
    }
    var list = listOf<String>("apple","orange","alalala","aaaaa")
    describe(list)
    filter(list)
}

fun getStrLength(str: Any): Int? {
    if (str is String && str.length > 0) {
        for (c in str)
            println(c)
        return str.length
    }
    return null
}

fun describe(obj: Any): String =
        when (obj) {
            1 -> "one"
            "Hello" -> "Greeting"
            is Long -> "Long"
            !is String -> "Not a String"
            else -> "Unknown"
        }

fun describe(arr:List<String>){
    when{
        "orange" in arr -> println("juicy")
        "apple" in arr -> println("apple is fine too")
    }
}
fun filter(fruits:List<String>){
    fruits.filter { it.startsWith("a") }.sortedBy { it }.map { it.toUpperCase() }.forEach { println(it) }
}