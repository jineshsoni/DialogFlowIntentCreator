import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dao.*
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.apache.xmlbeans.impl.xb.xsdschema.All
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
    log("sheetModelArray == $sheetModelArray")
//
//    log("Deleting Old Files")
//
//    resourceFolder.listFiles().forEach { file ->
//        if (file != inputFile) {
//            log("Deleting == ${file.name}")
//            file.delete()
//        }
//    }

    val distinctKeys = sheetModelArray[0].rowDataArray.distinctBy { it.key }

    distinctKeys.forEach { distKeys->
        log("Key == ${distKeys.key}")
        val addDataWithThisKey = sheetModelArray[0].rowDataArray.filter{it.key == distKeys.key}
        log("All data with ${distKeys.key} == $addDataWithThisKey")
        val fileNamePrefix = distKeys.key
        val allJson = AllJson()
        allJson.name = fileNamePrefix.stringCellValue
        val responses = ArrayList<Response>()
        val resp = Response()

        val params = Parameter()
        val message = Message()
        params.name = distKeys.entity.stringCellValue
        message.speech = distKeys.entity.stringCellValue

        val paramsArray = ArrayList<Parameter>()
        paramsArray.add(params)

        val messageArray = ArrayList<Message>()
        messageArray.add(message)

        resp.parameters = paramsArray
        resp.messages = messageArray
        responses.add(resp)

        allJson.responses = responses
        val allJsonJSON = Gson().toJson(allJson)

        val allJsonFile = File("$resPath/output/${fileNamePrefix}_all.json")
        allJsonFile.writeText(allJsonJSON)


        log(" allJson JSON == $allJsonJSON")

        val userSaysEnArray = ArrayList<UserSaysEn>()

    }


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

    val sheetModel = SheetModel()
    sheetModel.name = sheet.sheetName

    log("sheet == $sheet")

    val dataArray = ArrayList<RowData>()

    rows.forEach { currentRow ->



        /***
        val key = currentRow.getCell(0)
        val productName = currentRow.getCell(1)
        val entity = currentRow.getCell(2)
        val speechText = currentRow.getCell(3)

         */

        if (currentRow.getCell(0).stringCellValue != "") {

            val data = RowData(
                key = currentRow.getCell(0),
                productName = currentRow.getCell(1),
                entity = currentRow.getCell(2),
                speechText = currentRow.getCell(3)
            )

//            log("currentRow  data == $data")
            dataArray.add(data)
        }
    }

    sheetModel.rowDataArray = dataArray
    parsedData(sheetModel)
    log("----------")
}