package Cafe

import Cafe.MenuList.standardMenu

import scala.collection.IterableOnce.iterableOnceExtensionMethods
import scala.math.BigDecimal.double2bigDecimal

object CafeLogic extends App {

  val order1 = Order(List(MenuList.standardMenu(2), MenuList.standardMenu(7)))
  val order2 = Order(List(MenuList.standardMenu(2), MenuList.standardMenu(5), MenuList.standardMenu(9)))
  //val order3 = Order(List(MenuList.standardMenu(2), MenuList.standardMenu(10)))

  def stockCountTracker(item: MenuItem): Either[String, Int] = {
    item.stockCount match {
      case item if item <= 0 => Left("This item is out of stock")
      case item if item >= 1 => Right(item - 1)
    }
  }

  def createOrder(selectedItems: List[MenuItem]): Either[String, Order] = {
    selectedItems match {
      case Nil => Left("No items selected")
      case items if items.exists(item => !standardMenu.contains(item)) => Left("One of the selected items does not exist")
      case items =>
        val checkStock = items.map(stockCountTracker)
        if (checkStock.forall(_.isRight)) {
        Right(Order(selectedItems))
        } else {
          Left("One of the selected items is out of stock.")
        }
    }
  }

  def calculateServiceCharge(order: Order, customServiceCharge: Option[Double] = None): Double = {
    val totalAmount = order.orderTotal
    val serviceCharge =
      if (order.items.exists(_.foodType == Premium)) {
        totalAmount * 0.25
      } else if (order.items.exists(_.foodType == HotFood)) {
        totalAmount * 0.20
      } else if (order.items.exists(_.foodType == ColdFood)) {
        totalAmount * 0.10
      } else if (order.items.exists(_.foodType == Drink)) {
        totalAmount * 0.0
      } else {
        0.0
      }
    customServiceCharge.getOrElse(serviceCharge)
  }

  def createABill(order: Order, customServiceCharge: Option[Double] = None): String = {
    val totalAmount = order.orderTotal
    val finalServiceCharge = calculateServiceCharge(order, customServiceCharge)
    val finalTotal = totalAmount + finalServiceCharge
    val roundedTotal = BigDecimal(finalTotal).setScale(2, BigDecimal.RoundingMode.HALF_UP)
    val itemPerLine = order.items.map(item => s"${item.name}: £${item.price}").mkString("\n")
    s"Receipt:\n$itemPerLine\nTotal: £$roundedTotal"
  }

  val asparagus = MenuList.addPremiumItem(PremiumItem("Asparagus Eggs Benedict", 15.00, Premium, 10))
  MenuList.allItems.foreach(item => println(s"${item.name}: £${item.price}"))
  val strawbs = MenuList.addPremiumItem(PremiumItem("Strawberries and Cream", 7.50, Premium, 10))
  MenuList.allItems.foreach(item => println(s"${item.name}: £${item.price}"))
}

