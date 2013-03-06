package fixtures

import util.Ipsum._
import models.{ User, DAL }
import models.AppDB._
import slick.session.Session
import java.sql.Timestamp

object UsersFixture {
  def createFixtures(dal: DAL) = {

    val now = new java.util.Date()

    database withSession {
      implicit session: Session =>
        dal.Users.insertRows(
          //   Id    Created                     Updated                     Active Time                 Name          Email                  Password Hash  Salt  Status
          User(None, new Timestamp(now getTime), new Timestamp(now getTime), new Timestamp(now getTime), "Joe Bloggs", "jbloggs@example.com", "", "", 1).withNewPassword("foo"),
          User(None, new Timestamp(now getTime), new Timestamp(now getTime), new Timestamp(now getTime), "Plain Jane", "pjane@example.com", "", "", 1).withNewPassword("bar"))
    }
  }
}