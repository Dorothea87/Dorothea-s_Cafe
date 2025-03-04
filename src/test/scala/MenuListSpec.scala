import Cafe.{MenuItem, MenuList, PremiumItem}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class MenuListSpec extends AnyWordSpec with Matchers {


  "addPremiumItem" should {
    "return Some with an item" when {
      "adding a new premium item" in {
        val newItem = PremiumItem("Truffle Scrambled Eggs", 19.99)
        val result = MenuList.addPremiumItem(newItem)

        result shouldBe Some(newItem)
      }
    }

    "return None" when {
      "adding an existing Item" in {
        val existingItem = PremiumItem("Dorothea's Dream Cake", 4.75)
        MenuList.addPremiumItem(existingItem)
        val result = MenuList.addPremiumItem(existingItem)

        result shouldBe None
      }
    }
  }

  "allItems" should {
    "return only the standard Menu" when {
      "no premium items are added" in {
        val items = MenuList.allItems

        items shouldEqual MenuList.standardMenu
      }
    }


    "return a full list of all items" when {
      "appending the standard Menu with the premium Items" in {
        val newItem = PremiumItem("Truffle Scrambled Eggs", 19.99)
        MenuList.addPremiumItem(newItem)

        val result = MenuList.allItems

        result should contain allElementsOf List(MenuList.standardMenu: _*)
        result should contain(MenuItem(newItem.name, newItem.price))
      }
    }

    "not include duplicate premium items" when {
      "appending the standard Menu with the premium items" in {
        val existingItem = PremiumItem("Strawberry Dream Pancaketower", 19.99)
        MenuList.addPremiumItem(existingItem)
        MenuList.addPremiumItem(existingItem)

        val result = MenuList.allItems

        result should contain(MenuItem(existingItem.name, existingItem.price))
        result.count(_.name == existingItem.name) shouldBe 1
      }
    }
  }

}
