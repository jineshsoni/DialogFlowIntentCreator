import org.apache.poi.ss.usermodel.Cell
import java.io.File


data class RowData(
    val iosKey: Cell,
    val androidKey: Cell,
    val english: Cell,
    val portugies: Cell,
    val spanish: Cell,
    val german: Cell,
    val russian: Cell,
    val french: Cell,
    val italian: Cell,
    val greek: Cell,
    val hebrew: Cell
)

data class SheetModel(
    var name: String = "",
    var rowDataArray: ArrayList<RowData> = ArrayList()
)

