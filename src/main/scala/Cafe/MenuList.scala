package Cafe


object MenuList {
  val flatwhite = MenuItem("Flat white", 4.95, Drink, 250)
  val matchalatte = MenuItem("Matcha latte", 6.95, Drink, 60)
  val dorotea = MenuItem("Doro-tea", 2.95, Drink, 100)
  val baconbutty = MenuItem("Bacon butty", 5.55, HotFood, 35)
  val salmonbagel = MenuItem("Salmon bagel", 7.95, ColdFood, 45)
  val fullenglish = MenuItem("Full English", 9.85, HotFood, 45)
  val hallumifry = MenuItem("Hallumi fry-up", 8.95, HotFood, 45)
  val croissant = MenuItem("Croissant", 4.95, ColdFood, 20)
  val painsuisse = MenuItem("Pain au suisse", 5.75, ColdFood, 0)
  val dreamcake = MenuItem("Dorothea's Dream cake", 4.75, ColdFood, 16)

  var standardMenu: List[MenuItem] = List(
    flatwhite,
    matchalatte,
    dorotea,
    baconbutty,
    salmonbagel,
    fullenglish,
    hallumifry,
    croissant,
    painsuisse,
    dreamcake
    )

  private var premiumItems: List[PremiumItem] = List()

  def addPremiumItem(item: PremiumItem): Option[PremiumItem] = {
    if (premiumItems.exists(_.name == item.name)) {
      None
    } else {
      premiumItems = premiumItems :+ item
      Some(item)
    }
  }

  def allItems: List[MenuItem] = {
    standardMenu = standardMenu ++ premiumItems.map(item => MenuItem(item.name, item.price, item.foodType, item.stockCount))
    standardMenu
  }

  def removeAnItem(item: PremiumItem): List[PremiumItem] = {
    premiumItems = premiumItems.filterNot(_.name == item.name)
    premiumItems
  }
}

