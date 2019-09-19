package dao

import java.io.Serializable

data class UserSaysEn(
    val count: Int = 0,
    val data: List<Data> = ArrayList<Data>(),
//    val id: String,
    val isTemplate: Boolean = false,
    val updated: Int = 0
): Serializable

data class Data(
    var alias: String = "",
    val meta: String = if(alias=="") "" else "@$alias", //@ wala
    var text: String = "",
    val userDefined: Boolean = false
): Serializable