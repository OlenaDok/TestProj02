/**
 * @author Lena
 * 04/11/2019
 *
 * MAIN FUN
 * */

package il.ac.hit.olenad.app

import il.ac.hit.olenad.models.OrdersAnalyzer
import il.ac.hit.olenad.service.OrderParser
import org.json.JSONObject
import java.io.File


fun main() {
    val url2 = "./res/indata.json"

    val jsonData = JSONObject(File(url2).readText())

    val jsonData2 = jsonData.getJSONArray("orders")

    val listOfOrders = OrderParser().parseOrderObject(jsonData2)

    val statisticData = OrdersAnalyzer().totalDailySales(listOfOrders)

    println(statisticData)

}
