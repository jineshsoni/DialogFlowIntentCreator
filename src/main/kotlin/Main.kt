import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.File
import java.io.FileInputStream

fun main() {
    val start = System.currentTimeMillis()
    log("--Start--")

    val fileName = "input.xlsx"
    openFile(fileName)

    log("--End--")
    log("Process Completed in ${(System.currentTimeMillis() - start).toFloat() / 1000} seconds")
}

fun openFile(fileName: String) {
    val resPath = "src/main/resources"
    val resourceFolder = File(resPath)
    val inputFile = File("$resPath/$fileName")
    val excelFile = FileInputStream(inputFile)

    val workbook = XSSFWorkbook(excelFile)
    val count = workbook.numberOfSheets

    val sheetModelArray = ArrayList<SheetModel>()

    log("There are $count Sheets")

    workbook.sheetIterator().forEach { sheet ->
        if (sheet.sheetName == "Input") {
            log("Parsing Sheet == ${sheet.sheetName}")
            parseSheet(sheet) { sheetModel ->
                sheetModelArray.add(sheetModel)
            }
        }
    }

    log("Parsing Done")
//
//    log("Deleting Old Files")
//
//    resourceFolder.listFiles().forEach { file ->
//        if (file != inputFile) {
//            log("Deleting == ${file.name}")
//            file.delete()
//        }
//    }

    log("Generating Files")

    sheetModelArray.forEach {
        log("Generating --> ${it.name} String File")
    }

    workbook.close()
    excelFile.close()
}

fun parseSheet(sheet: Sheet, parsedData: (SheetModel) -> Unit) {

    val rows = sheet.iterator()
    val checkRow = sheet.iterator().iterator().next()

    if (checkRow.getCell(0).stringCellValue == "ios_key") {

        val sheetModel = SheetModel()
        sheetModel.name = sheet.sheetName

        val dataArray = ArrayList<RowData>()

        rows.forEach { currentRow ->

            /***
            val iosKey = currentRow.getCell(0)
            val androidKey = currentRow.getCell(1)
            val english = currentRow.getCell(2)
            val portugies = currentRow.getCell(3)
            val spanish = currentRow.getCell(4)
            val german = currentRow.getCell(5)
            val polish = currentRow.getCell(6)
            val russian = currentRow.getCell(7)
            val french = currentRow.getCell(8)
            val italian = currentRow.getCell(9)
            val greek = currentRow.getCell(10)
            val arabic = currentRow.getCell(11)
            val hebrew = currentRow.getCell(12)
            val japanese = currentRow.getCell(13)
            val chinese = currentRow.getCell(14)
             */

            if (currentRow.getCell(0).stringCellValue != "") {

                val data = RowData(
                    iosKey = currentRow.getCell(0),
                    androidKey = currentRow.getCell(1),
                    english = currentRow.getCell(2),
                    portugies = currentRow.getCell(3),
                    spanish = currentRow.getCell(4),
                    german = currentRow.getCell(5),
                    russian = currentRow.getCell(7),
                    french = currentRow.getCell(8),
                    italian = currentRow.getCell(9),
                    greek = currentRow.getCell(10),
                    hebrew = currentRow.getCell(12)
                )

                dataArray.add(data)


            }
        }

        sheetModel.rowDataArray = dataArray
        parsedData(sheetModel)
        log("----------")
    }
}