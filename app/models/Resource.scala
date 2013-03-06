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

    def all()(implicit session: Session): List[T] = {
      Query(this) list
    }

    protected def byId(id: Long) = for {
      q <- Query(this) if q.id === id
    } yield q

    def findById(id: Long)(implicit session: Session): Option[T] = {
      byId(id).firstOption
    }

    def deleteById(id: Long)(implicit session: Session): Boolean = {
      byId(id).delete > 0
    }

    def insertRows(rows: T*)(implicit session: Session) = {
      for (row <- rows) this.insert(row)
    }
  }
}