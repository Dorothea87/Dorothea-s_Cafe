package Cafe

case class MenuItem(name: String, price: Double, foodType: FoodType, stockCount: Int)

sealed trait FoodType

case object Drink extends FoodType

case object ColdFood extends FoodType

case object HotFood extends FoodType

case object Premium extends FoodType
