package repositories

import models.{ Resource, DAL }
import models.AppDB._
import slick.session.Session
import play.api.db.DB
import play.api.Play.current

abstract class ResourceRepo[T <: Resource](table: dal.Resources[T]) {
  
  import dal.profile.simple._
  
  protected def allEntities = for {
	  e <- table
  } yield e

  protected def byId(id: Long) = for {
	  e <- table if e.id === id
  } yield e
  
  protected def t_byId = for {
	  id <- Parameters[Long]
	  e <- table if e.id is id
  } yield e
  
  def all(): List[T] = {
    database withSession {
      implicit session: Session =>
        allEntities list
    }
  }

  /**
   * Find one Resource by ID
   */
  def findById(id: Long): Option[T] = {
    database withSession {
      implicit session: Session =>
        t_byId(id) firstOption
    }
  }

  /**
   * Delete one Resource by ID
   */
  def deleteById(id: Long): Boolean = {
    database withSession {
      implicit session: Session =>
        byId(id).delete > 0
    }
  }
  
  /**
   * Create one Resource
   */
  def create(entity: T): Boolean = {
    database withSession {
      implicit session: Session =>
        table.insert(entity) > 0
    }
  }
  
  /**
   * Create lots of Resources
   */
  def createAll(entities: T*): Boolean = {
    database withTransaction {
      implicit session: Session =>
        table.insertAll(entities:_*).fold(false)(_ > 0)
    }
  }
  
  /**
   * Update one Resource by ID
   */
  def save(entity: T): Boolean = {
    database withSession {
      implicit session: Session =>
        byId(entity.id.get).update(entity) > 0
    }
  }
}