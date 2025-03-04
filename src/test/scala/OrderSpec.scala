import Cafe.{MenuList, Order}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class OrderSpec extends AnyWordSpec with Matchers {

  "orderTotal" should {
    "return the total amount to pay" when {
      "an order is placed with several items" in {
        val order1 = Order(List(MenuList.standardMenu(2), MenuList.standardMenu(7)))
        val expectedResult = 7.9

        order1.orderTotal shouldBe expectedResult
      }
    }

    "return zero" when {
      "an order is empty" in {
        val orderEmpty = Order(List())
        val expectedResult = 0

        orderEmpty.orderTotal shouldBe expectedResult
      }
    }
  }



}
