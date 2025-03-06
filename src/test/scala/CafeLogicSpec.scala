import Cafe.CafeLogic.{calculateServiceCharge, createABill, createOrder}
import Cafe.{ColdFood, Drink, HotFood, MenuItem, MenuList, Order, Premium}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class CafeLogicSpec extends AnyWordSpec with Matchers {

  "createOrder" should {
    "return a Right with a valid order" when {
      "items are selected from a list" in {
        val selectedItems = List(MenuList.standardMenu(1), MenuList.standardMenu(3))

        val order = createOrder(selectedItems)

        order shouldBe Right(Order(selectedItems))

      }
    }

    "return a Left with an invalid order" when {
      "no items are selected from the list" in {
        val selectedItems = List()

        val order = createOrder(selectedItems)

        order shouldBe Left("No items selected")
      }
    }

    "return a Left with an invalid order" when {
      "an item that is selected is not in the list" in {
        val invalidItem = MenuItem("This is no longer on the menu", 5.00, ColdFood)
        val selectedItems = List(MenuList.standardMenu(4), invalidItem)

        val order = createOrder(selectedItems)

        order shouldBe Left("One of the selected items does not exist")
      }
    }
  }

  "calculateServiceCharge" should {
    "return a 25% service charge" when {
      "a premium item is in the order" in {
        val order = Order(List(MenuItem("Expensive fancy salmon", 20.00, Premium)))
        val serviceCharge = calculateServiceCharge(order)

        serviceCharge shouldBe 5.00
      }
    }

    "return a 20% service charge" when {
      "hot food is in the order" in {
        val order = Order(List(MenuItem("Coffee", 2.00, Drink), MenuItem("Scrambled Eggs", 5.50, HotFood)))
        val serviceCharge = calculateServiceCharge(order)

        serviceCharge shouldBe 1.50
      }
    }

    "return a 10% service charge" when {
      "cold food is in the order" in {
        val order = Order(List(MenuItem("Coffee", 2.00, Drink), MenuItem("Sandwich", 5.50, ColdFood)))
        val serviceCharge = calculateServiceCharge(order)

        serviceCharge shouldBe 0.75
      }
    }

    "return no service charge" when {
      "no food type matches" in {
        val order = Order(List(MenuItem("Coffee", 2.00, null), MenuItem("Chai Latte", 5.50, null)))
        val serviceCharge = calculateServiceCharge(order)

        serviceCharge shouldBe 0.0
      }
    }

    "return no service charge" when {
      "only drinks are in the order" in {
        val order = Order(List(MenuItem("Coffee", 2.00, Drink), MenuItem("Chai Latte", 5.50, Drink)))
        val serviceCharge = calculateServiceCharge(order)

        serviceCharge shouldBe 0.0
      }
    }

    "return a custom service charge" when {
      "a custom service charge was set" in {
        val order = Order(List(MenuItem("Coffee", 2.00, Drink), MenuItem("Sandwich", 6.00, ColdFood), MenuItem("Truffle Scrambled Eggs", 18.00, Premium)))
        val serviceCharge = calculateServiceCharge(order, Some(15.00))

        serviceCharge shouldBe 15.00
      }
    }
  }

  "createABill" should {
    "return a String with all the items in the order and the total incl service charge" when {
      "an order is put into the system" in {
        val order1 = Order(List(MenuList.standardMenu(2), MenuList.standardMenu(7)))
        val bill = createABill(order1)
        val expectedResult = "Receipt:\nDoro-tea: £2.95\nCroissant: £4.95\nTotal: £8.69"

        bill shouldBe expectedResult

      }
    }


  }
}
