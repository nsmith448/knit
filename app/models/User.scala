package models

import java.sql.{ Date, Timestamp }
import slick.driver.H2Driver._
import util.Security

case class User(
  id: Option[Long],
  created_time: Timestamp,
  updated_time: Timestamp,
  active_time: Timestamp,
  name: String,
  email: String,
  password_hash: String,
  salt: String,
  status: Int)
  extends Resource {

  def withNewPassword(plaintext: String): User = {
    val newSalt = Security generateSalt
    val newHash = Security hashPassword (plaintext, salt)

    new User(id, created_time, updated_time, active_time, name, email, newHash, newSalt, status).changed
  }
}

trait UserComponent extends ResourceComponent {
  this: Profile =>

  import profile._

  object Users extends Resources[User]("users") {
    def active_time = column[Timestamp]("active_time")
    def name = column[String]("name")
    def email = column[String]("email")
    def password_hash = column[String]("password_hash")
    def salt = column[String]("salt")
    def status = column[Int]("status")
    def * = id.? ~ created_time ~ updated_time ~ active_time ~ name ~ email ~ password_hash ~ salt ~ status <> (User, User.unapply _)
  }
}