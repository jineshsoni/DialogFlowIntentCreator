import dao.AllJson
import dao.UserSaysEn
import org.apache.poi.ss.usermodel.Cell
import java.io.File


data class RowData(
    val key: Cell,
    val productName: Cell,
    val entity: Cell,
    val speechText: Cell
)

data class SheetModel(
    var name: String = "",
    var rowDataArray: ArrayList<RowData> = ArrayList()
){

}

data class DataSheet(
    val allJson: AllJson = AllJson(),
    val userDaysEnArray: ArrayList<UserSaysEn> = ArrayList()
)

