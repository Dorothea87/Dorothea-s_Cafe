package Cafe

case class Order(items: List[Menu]) {
def orderTotal: Double = items.map(_.price).sum

}
