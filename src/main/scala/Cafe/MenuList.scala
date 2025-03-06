package Cafe


object MenuList {
  val flatwhite = MenuItem("Flat white", 4.95, Drink)
  val matchalatte = MenuItem("Matcha latte", 6.95, Drink)
  val dorotea = MenuItem("Doro-tea", 2.95, Drink)
  val baconbutty = MenuItem("Bacon butty", 5.55, HotFood)
  val salmonbagel = MenuItem("Salmon bagel", 7.95, ColdFood)
  val fullenglish = MenuItem("Full English", 9.85, HotFood)
  val hallumifry = MenuItem("Hallumi fry-up", 8.95, HotFood)
  val croissant = MenuItem("Croissant", 4.95, ColdFood)
  val painsuisse = MenuItem("Pain au suisse", 5.75, ColdFood)
  val dreamcake = MenuItem("Dorothea's Dream cake", 4.75, ColdFood)

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
  //still need to remove premiums, only adds atm

  def allItems: List[MenuItem] = {
    standardMenu = standardMenu ++ premiumItems.map(item => MenuItem(item.name, item.price, item.foodType))
    standardMenu
  }
}

