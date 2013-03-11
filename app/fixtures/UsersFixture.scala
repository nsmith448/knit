package fixtures

import java.sql.Timestamp

import models.DAL
import models.User
import repositories.UserRepo

object UsersFixture extends Fixture {
  def createFixtures = {

    val now = new java.util.Date()

    UserRepo.createAll(
      //   Id    Created                     Updated                     Active Time                 Name          Email                  Password Hash  Salt  Status
      User(None, new Timestamp(now getTime), new Timestamp(now getTime), new Timestamp(now getTime), "Joe Bloggs", "jbloggs@example.com", "",            "",   1).withNewPassword("foo"),
      User(None, new Timestamp(now getTime), new Timestamp(now getTime), new Timestamp(now getTime), "Plain Jane", "pjane@example.com",   "",            "",   1).withNewPassword("bar"))
  }
}