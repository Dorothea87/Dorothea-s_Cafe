package Cafe

object MenuList {
  val flatwhite = MenuItem("Flat white", 4.95)
  val matchalatte = MenuItem("Matcha latte", 6.95)
  val dorotea = MenuItem("Doro-tea", 2.95)
  val baconbutty = MenuItem("Bacon butty", 5.55)
  val salmonbagel = MenuItem("Salmon bagel", 7.95)
  val fullenglish = MenuItem("Full English", 9.85)
  val hallumifry = MenuItem("Hallumi fry-up", 8.95)
  val croissant = MenuItem("Croissant", 4.95)
  val painsuisse = MenuItem("Pain au suisse", 5.75)
  val dreamcake = MenuItem("Dorothea's Dream cake", 4.75)

  val standardMenu: List[MenuItem] = List(
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
    standardMenu ++ premiumItems.map(item => MenuItem(item.name, item.price))
  }
}
