package dao

data class UserSaysEn(
    val count: Int = 0,
    val data: List<Data> = ArrayList<Data>(),
//    val id: String,
    val isTemplate: Boolean = false,
    val updated: Int = 0
)

data class Data(
    var alias: String = "",
    val meta: String = if(alias=="") "" else "@$alias", //@ wala
    var text: String = "",
    val userDefined: Boolean = false
)