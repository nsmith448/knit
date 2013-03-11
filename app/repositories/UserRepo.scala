package repositories

import models.User
import models.AppDB._
import play.api._
import play.api.Play.current
import slick.session.Session

object UserRepo extends ResourceRepo[User, dal.Users.type](dal.Users) {

  import dal.profile.simple._

  protected def t_byEmail = for {
    email <- Parameters[String]
    u <- table if u.email === email
  } yield u

  def findByEmail(email: String): Option[User] = {
    database withSession {
      implicit session: Session =>
        t_byEmail(email).firstOption
    }
  }
}