package Cafe


case class Order(items: List[MenuItem]) {
def orderTotal: Double = items.map(_.price).sum
}