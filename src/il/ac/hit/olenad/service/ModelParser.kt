/**
 * @author Lena
 * 04/11/2019
 *
 * JSONParser
 * */

package il.ac.hit.olenad.service

import il.ac.hit.olenad.models.Order
import il.ac.hit.olenad.models.OrderLine
import org.json.JSONArray
import org.json.JSONObject
import java.time.LocalDateTime
import java.util.ArrayList

class SingleOrderLineParser {

     fun parseObjectLine(obj: JSONObject ): OrderLine{

         return OrderLine(
             obj.getInt("productId"),
             obj.getString("name"),
             obj.getInt("quantity"),
             obj.getBigDecimal("unitPrice")
         )
    }
}

class OrderLineParser {

     fun parseListObject(obj: JSONArray ): List<OrderLine>{

        val size: Int = obj.length()
        val list = ArrayList<OrderLine>(size)

        for (i in 0..size-1) {
            val orderLine: OrderLine = SingleOrderLineParser().parseObjectLine(obj[i] as JSONObject)

            list.add(orderLine)
        }

        return list
    }
}

class OrderParser {

     fun parseOrderObject(obj: JSONArray ): List<Order>{

         val size: Int = obj.length()
         val list = ArrayList<Order>(size)

         for (i in 0..size-1) {
             val id = (obj[i] as JSONObject).getInt("orderId")
             val date = LocalDateTime.parse((obj[i] as JSONObject).getString("creationDate"))

             val lines = OrderLineParser().parseListObject((obj[i] as JSONObject).getJSONArray("orderLines"))

             val item = Order(id, date, lines)

             list.add(item)
         }

         return list
    }
}
