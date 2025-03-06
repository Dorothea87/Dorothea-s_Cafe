package Cafe

import Cafe.MenuList.standardMenu

import scala.math.BigDecimal.double2bigDecimal

object CafeLogic extends App {

  val order1 = Order(List(MenuList.standardMenu(2), MenuList.standardMenu(7)))
  val order2 = Order(List(MenuList.standardMenu(2), MenuList.standardMenu(5), MenuList.standardMenu(9)))

  def createOrder(selectedItems: List[MenuItem]): Either[String, Order] = {
    selectedItems match {
      case Nil => Left("No items selected")
      case items if items.exists(item => !standardMenu.contains(item)) => Left("One of the selected items does not exist")
      case items => Right(Order(selectedItems))
    }
  }

  def calculateServiceCharge(order: Order, customServiceCharge: Option[Double] = None): Double = {
    val totalAmount = order.orderTotal
    val serviceCharge = if (order.items.exists(_.foodType == Premium)) {
      totalAmount * 0.25
    } else if (order.items.exists(_.foodType == HotFood)) {
      totalAmount * 0.20
    } else if (order.items.exists(_.foodType == ColdFood)) {
      totalAmount * 0.10
    } else if (order.items.exists(_.foodType == Drink)) {
      totalAmount
    } else {
      0.0
    }
    customServiceCharge.getOrElse(serviceCharge)
  }

  def createABill(order:Order, customServiceCharge: Option[Double] = None): String = {
    val totalAmount = order.orderTotal
    val finalServiceCharge = calculateServiceCharge(order, customServiceCharge)
    val finalTotal = totalAmount + finalServiceCharge
    val roundedTotal = BigDecimal(finalTotal).setScale(2, BigDecimal.RoundingMode.HALF_UP)
    val itemPerLine = order.items.map(item => s"${item.name}: £${item.price}").mkString("\n")
    s"Receipt:\n$itemPerLine\nTotal: £$roundedTotal"
  }

  //  println(createABill(order1.items, order1.orderTotal))
  println(createABill(order2))
  println(createABill(order1))

  val asparagus = MenuList.addPremiumItem(PremiumItem("Asparagus Eggs Benedict", 15.00, Premium))
  MenuList.allItems.foreach(item => println(s"${item.name}: £${item.price}"))

  val order3 = Order(List(MenuList.standardMenu(2), MenuList.standardMenu(10)))

  println(order3.items)


  /** SERVICE CHARGE */
  //Method that tallies up my order and adds service charge
  //pattern matching ? only drink => no service charge, cold food => 10%, hot food => 20%, premium special => 25%, custom value =>
  //this will eventually go into the calculateService charge

  /** STOCK COUNT */
  //create some conditional logic that represents when the stock count is 0, item can't be ordered anymore
  //take one item off the count every time it is ordered
}

