import Cafe.CafeLogic.createABill
import Cafe.{MenuList, Order}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class CafeLogicSpec extends AnyWordSpec with Matchers {

  "createABill" should {
    "return a String with all the items in the order and the total" when {
      "an order is put into the system" in {
        val order1 = Order(List(MenuList.standardMenu(2), MenuList.standardMenu(7)))
        val expectedResult = "Receipt:\nDoro-tea: £2.95\nCroissant: £4.95\nTotal: £7.9"
        val result = createABill(order1.items, 7.9)

        result shouldBe expectedResult

      }
    }

    "return a string saying 'Total: £ 0.0'" when {
      "an empty order is put into the system" in {
        val orderEmpty = Order(List())
        val expectedResult = "Receipt:\n\nTotal: £0.0"
        val result = createABill(orderEmpty.items, 0.0)

        result shouldBe expectedResult

      }
    }
  }
}
