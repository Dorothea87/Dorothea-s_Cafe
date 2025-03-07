import Cafe.{MenuItem, MenuList, Premium, PremiumItem}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec


class MenuListSpec extends AnyWordSpec with Matchers {


  "addPremiumItem" should {
    "return Some with an item" when {
      "adding a new premium item" in {
        val newItem = PremiumItem("Truffle Scrambled Eggs", 19.99, Premium, 15)
        val result = MenuList.addPremiumItem(newItem)

        result shouldBe Some(newItem)
      }
    }

    "return None" when {
      "adding an existing Item" in {
        val existingItem = PremiumItem("Dorothea's Dream Cake", 4.75, Premium, 15)
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
        val newItem = PremiumItem("Truffle Scrambled Eggs", 19.99, Premium, 15)
        MenuList.addPremiumItem(newItem)

        val result = MenuList.allItems

        result should contain allElementsOf List(MenuList.standardMenu: _*)
        result should contain(MenuItem(newItem.name, newItem.price, Premium, 15))
      }
    }

    "not include duplicate premium items" when {
      "appending the standard Menu with the premium items" in {
        val existingItem = PremiumItem("Strawberry Dream Pancaketower", 19.99, Premium, 15)
        MenuList.addPremiumItem(existingItem)
        MenuList.addPremiumItem(existingItem)

        val result = MenuList.allItems

        result should contain(MenuItem(existingItem.name, existingItem.price, existingItem.foodType, existingItem.stockCount))
        result.count(_.name == existingItem.name) shouldBe 1
      }
    }
  }

//  "removeAnItem" should {
//    "remove an item from a list" when {
//      "the name of the item is entered" in {
//        val item1 = PremiumItem("Truffle Pasta", 19.99, Premium, 20)
//        val item2 = PremiumItem("Lobster Roll", 24.00, Premium, 15)
//
//        MenuList.addPremiumItem(item1)
//        MenuList.addPremiumItem(item2)
//
//        val menu = MenuList.removeAnItem(item1)
//
//        val result = MenuList
//
//        // Validate that item1 is removed but item2 is still present
//        result should not contain (MenuItem(item1.name, item1.price, item1.foodType, item1.stockCount))
//        result should contain(MenuItem(item2.name, item2.price, item2.foodType, item2.stockCount))
//        //        MenuList should have size 1
//      }
//    }
//  }

}
