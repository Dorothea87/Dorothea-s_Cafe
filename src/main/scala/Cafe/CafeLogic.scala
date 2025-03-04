package Cafe

object CafeLogic extends App {

  val order1 = Order(List(MenuList.standardMenu(2), MenuList.standardMenu(7)))
  val order2 = Order(List(MenuList.standardMenu(2), MenuList.standardMenu(5), MenuList.standardMenu(9)))

  //  val total = order1.orderTotal
  //  println(s"Your total is: £$total")


  def createOrder(selectedItems: List[MenuItem]): Order = {
    Order(selectedItems)
  }

  def calculateServiceCharge(): Double = ???

  def createABill(items: List[MenuItem], total: Double): String = {
    val itemPerLine = items.map(item => s"${item.name}: £${item.price}").mkString("\n")
    s"Receipt:\n$itemPerLine\nTotal: £${total}"
  }

  //  println(createABill(order1.items, order1.orderTotal))
  println(createABill(order2.items, order2.orderTotal))
  println(createABill(order1.items, order1.orderTotal))

  val asparagus = MenuList.addPremiumItem(PremiumItem("Asparagus Eggs Benedict", 15.00, Prem))
  MenuList.allItems.foreach(item => println(s"${item.name}: £${item.price}"))

  val order3 = Order(List(MenuList.standardMenu(2), MenuList.standardMenu(10)))

  println(order3)

  println(createOrder(List(MenuList.dorotea, MenuList.dreamcake)))


  //this is my to do list (sorry, I'm slow)

  /** SERVICE CHARGE */
  //Method that tallies up my order and adds service charge
  //pattern matching ? only drink => no service charge, cold food => 10%, hot food => 20%, premium special => 25%, custom value =>
  //this will eventually go into the calculateService charge

  /** STOCK COUNT */
  //create some conditional logic that represents when the stock count is 0, item can't be ordered anymore
  //take one item off the count every time it is ordered
}

