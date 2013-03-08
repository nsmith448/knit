package models

trait Ordered {
  def order: Int
}

abstract trait OrderedComponent {
  this: Profile =>
	  
  import profile.simple._
  
  abstract trait OrderedRows[T] {
    this: Table[T] =>
	  
    def order = column[Int]("order")
    
	def inOrder(q: Query[OrderedRows[T], T]) = q.sortBy(_.order.asc) 
  }
}