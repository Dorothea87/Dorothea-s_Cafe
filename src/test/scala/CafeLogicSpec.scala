import Cafe.CafeLogic.{calculateServiceCharge, createABill, createOrder, stockCountTracker}
import Cafe.MenuList._
import Cafe._
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class CafeLogicSpec extends AnyWordSpec with Matchers {

  val selectedItems = List(MenuList.matchalatte, MenuList.baconbutty)
  val orderDrinks = Order(List(MenuList.flatwhite, MenuList.dorotea))
  val orderColdFood = Order(List(MenuList.dorotea, MenuList.croissant))
  val orderHotFood = Order(List(MenuList.baconbutty, MenuList.dreamcake))
  val orderPremiumFood = Order(List(MenuItem("Expensive fancy salmon", 20.00, Premium, 10)))
  val itemInStock = MenuItem("Barbecue chicken breakfast", 12.95, HotFood, 9)

  "stockCountTracker" should {
    "return a Left with an error message" when {
      "an item is out of stock" in {
        val item = MenuItem("Chicken sando", 8.95, HotFood, 0)
        val result = stockCountTracker(item)

        result shouldBe Left("This item is out of stock")
      }
    }

    "return a Right with the correct stock count" when {
      "an item is available" in {

        val result = stockCountTracker(itemInStock)

        result shouldBe Right(8)
      }
    }
  }


  "createOrder" should {
    "return a Right with a valid order" when {
      "items are selected from a list" in {
        val order = createOrder(selectedItems)

        order shouldBe Right(Order(selectedItems))

      }
    }

    "return a Left with in error" when {
      "items are out of stock" in {
        val order = createOrder(List(painsuisse))

        order shouldBe Left("One of the selected items is out of stock.")

      }
    }

    "return a Left with an invalid order error" when {
      "no items are selected from the list" in {
        val emptyList = List()
        val order = createOrder(emptyList)

        order shouldBe Left("No items selected")
      }
    }

    "return a Left with an invalid order error" when {
      "an item that is selected is not in the list" in {
        val invalidItem = MenuItem("This is no longer on the menu", 5.00, ColdFood, 50)
        val selectedItems = List(MenuList.standardMenu(4), invalidItem)

        val order = createOrder(selectedItems)

        order shouldBe Left("One of the selected items does not exist")
      }
    }
  }

  "calculateServiceCharge" should {
    "return a 25% service charge" when {
      "a premium item is in the order" in {

        val serviceCharge = calculateServiceCharge(orderPremiumFood)

        serviceCharge shouldBe 5.00
      }
    }

    "return a 20% service charge" when {
      "hot food is in the order" in {
        val serviceCharge = calculateServiceCharge(orderHotFood)

        serviceCharge shouldBe 2.06
      }
    }

    "return a 10% service charge" when {
      "cold food is in the order" in {
        val serviceCharge = calculateServiceCharge(orderColdFood)

        serviceCharge shouldBe 0.79
      }
    }

    "return no service charge" when {
      "no food type matches" in {
        val order = Order(List(MenuItem("Coffee", 2.00, null, 0), MenuItem("Chai Latte", 5.50, null, 0)))
        val serviceCharge = calculateServiceCharge(order)

        serviceCharge shouldBe 0.0
      }
    }

    "return no service charge" when {
      "only drinks are in the order" in {
        val serviceCharge = calculateServiceCharge(orderDrinks)

        serviceCharge shouldBe 0.0
      }
    }

    "return a custom service charge" when {
      "a custom service charge was set" in {
        val serviceCharge = calculateServiceCharge(orderHotFood, Some(15.00))

        serviceCharge shouldBe 15.00
      }
    }
  }

  "createABill" should {
    "return a String with all the items in the order and the total incl service charge" when {
      "an order is placed" in {
        val bill = createABill(orderColdFood)
        val expectedResult = "Receipt:\nDoro-tea: £2.95\nCroissant: £4.95\nTotal: £8.69"

        bill shouldBe expectedResult

      }
    }

    "return a String with all the items in the order and the total incl service charge" when {
      "an order is placed containing hot food" in {
        val bill = createABill(orderHotFood)
        val expectedResult = "Receipt:\nBacon butty: £5.55\nDorothea's Dream cake: £4.75\nTotal: £12.36"

        bill shouldBe expectedResult

      }
    }

//    "return a String with all the items in the order and the total incl service charge" when {
//      "an order is placed containing a premium item" in {
//        val bill = createABill(orderPremiumFood)
//        val expectedResult = "Receipt:\nExpensive fancy salmon: £20.00\nTotal: £25.00"
//
//        bill shouldBe expectedResult
//
//      }
//    }

    "return a String with all the items in the order and the total incl service charge" when {
      "an order is placed and an optional service charge is selected" in {
        val customServiceCharge = Some(10.00)
        val bill = createABill(orderColdFood, customServiceCharge)
        val expectedResult = "Receipt:\nDoro-tea: £2.95\nCroissant: £4.95\nTotal: £17.90"

        bill shouldBe expectedResult

      }
    }
    //order with different items e.g. premium, hot food

  }
}

