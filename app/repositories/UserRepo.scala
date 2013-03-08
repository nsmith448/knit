package repositories

import models.{ User, DAL }
import models.AppDB._
import play.api._
import play.api.Play.current
import slick.session.Session

object UserRepo extends ResourceRepo(dal.Users) {
  
  def findByEmail(email: String): Option[User] = {
    database withSession {
      implicit session: Session =>
      dal.Users.findByEmail(email)
    }
  }
}