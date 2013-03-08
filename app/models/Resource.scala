package models

import java.sql.Timestamp
import java.util.{ Date => JavaDate }

abstract class Resource {
  def id: Option[Long]
  def created_time: Timestamp
  def updated_time: Timestamp

  def changed(): this.type = {
    updated_time.setTime(new JavaDate().getTime)
    this
  }

  override def toString() = {
    "[%d] " format id.getOrElse(-1L)
  }
}

trait ResourceComponent {
  self: Profile =>
    
  import profile.simple._

  abstract class Resources[T <: Resource](table: String) extends Table[T](table: String) {
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
    def created_time = column[Timestamp]("created_time")
    def updated_time = column[Timestamp]("updated_time")
  }

}