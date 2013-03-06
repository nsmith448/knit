package repositories
/*
import models.{ User, Users }
import scala.slick.driver.H2Driver.simple._
import Database.threadLocalSession
import play.api.db.DB
import play.api.Play.current

object UserRepo extends ResourceRepo[User](Users) {
	
  def findByEmail(email: String): Option[User] = {
    database withSession {
      (for { 
        u <- Users if u.email === email
      } yield u) firstOption
    }
  }
  
  def save(user: User): Boolean = {
    database withSession {
      byId(user.id.get).update(user) > 0
    }
  }
}*/ 