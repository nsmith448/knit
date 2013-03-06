package models

trait Prioritized {
  def priority: Int
}

object Priority {
  val CRITICAL = 1
  val HIGH = 2
  val NORMAL = 3
  val LOW = 4
  val BACKLOG = 5
}