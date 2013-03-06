package models

trait Trackable {

  val time_total: Float
  val time_spent: Float

  def tracked(total: Float, spent: Float): Trackable
}