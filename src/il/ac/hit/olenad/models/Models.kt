/**
* @author Lena
* 04/11/2019
 *
 * Data Models
* */

package il.ac.hit.olenad.models

import java.math.BigDecimal
import java.time.DayOfWeek
import java.time.LocalDateTime


data class Order(
    val orderId: Int,
    val creationDate: LocalDateTime,
    val orderLines: List<OrderLine>
)

data class OrderLine(
    val productId: Int,
    val name: String,
    val quantity: Int,
    val unitPrice: BigDecimal
)


class OrdersAnalyzer {

    fun totalDailySales(orders: List<Order>): Map<DayOfWeek, Int> {

        val subResult = mutableMapOf<DayOfWeek, Int>()

        var numSunday = 0
        var numMonday = 0
        var numTUESDAY = 0
        var numWEDNESDAY = 0
        var numTHURSDAY = 0
        var numFRIDAY = 0
        var numSATURDAY = 0

        for (i in 0..(orders.size - 1)){
                 when (orders[i].creationDate.dayOfWeek) {
                    DayOfWeek.SUNDAY -> numSunday += getNumberOfOrders(orders[i].orderLines)
                    DayOfWeek.MONDAY -> numMonday += getNumberOfOrders(orders[i].orderLines)
                    DayOfWeek.TUESDAY -> numTUESDAY += getNumberOfOrders(orders[i].orderLines)
                    DayOfWeek.WEDNESDAY -> numWEDNESDAY += getNumberOfOrders(orders[i].orderLines)
                    DayOfWeek.THURSDAY -> numTHURSDAY += getNumberOfOrders(orders[i].orderLines)
                    DayOfWeek.FRIDAY -> numFRIDAY += getNumberOfOrders(orders[i].orderLines)
                    DayOfWeek.SATURDAY -> numSATURDAY += getNumberOfOrders(orders[i].orderLines)
                }

        }
        subResult.put(DayOfWeek.SUNDAY , numSunday)
        subResult.put(DayOfWeek.MONDAY , numMonday)
        subResult.put(DayOfWeek.TUESDAY , numTUESDAY)
        subResult.put(DayOfWeek.WEDNESDAY , numWEDNESDAY)
        subResult.put(DayOfWeek.THURSDAY , numTHURSDAY)
        subResult.put(DayOfWeek.FRIDAY , numFRIDAY)
        subResult.put(DayOfWeek.SATURDAY , numSATURDAY)

     //   println("num sunday from res "   + result.getValue(DayOfWeek.SATURDAY))

        return subResult.toMap().filterValues { it != 0 }
    }

    private fun getNumberOfOrders(order: List<OrderLine>): Int {

        var number = 0

      //  println(order)

        for(i in 0..order.size-1){
            number+= + order[i].quantity
        }

        return number
    }
}

