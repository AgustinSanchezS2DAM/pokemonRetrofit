package net.azarquiel.retrofixinirx.model

/**
 * Created by pacopulido on 28/1/18.
 */

data class Pokemon(val results:List<Result>)
data class Result(val url: String, val name:String)
data class Tema(val _id:Long, val descripcion:String)

