package repositories

import models.{ Resource, DAL }
import models.AppDB._
import slick.session.Session
import play.api.db.DB
import play.api.Play.current

abstract class ResourceRepo[E <: Resource](table: dal.Resources[E]) {

  def all(): List[E] = {
    database withSession {
      implicit session: Session =>
        table.all
    }
  }

  def findById(id: Long): Option[E] = {
    database withSession {
      implicit session: Session =>
        table.findById(id)
    }
  }

  def deleteById(id: Long): Boolean = {
    database withSession {
      implicit session: Session =>
        table.deleteById(id)
    }
  }
}