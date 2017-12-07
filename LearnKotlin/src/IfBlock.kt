/**
 * Created by 向博文 on 2017/12/6.
 */
fun main(args: Array<String>) {
    println(maxOf(12,15))
    println(mixOf(14,13))
}

fun maxOf(a:Int,b:Int):Int{
    if(a>b){
        return a
    }else{
        return b
    }
}

fun mixOf(a:Int,b:Int) = if(a<b) a else b