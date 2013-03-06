package models

import java.sql.Timestamp
import slick.session.Session
import repositories.SprintRepo

case class Project(
  id: Option[Long],
  created_time: Timestamp,
  updated_time: Timestamp,
  title: String,
  description: String,
  status: Int,
  order: Int,
  priority: Int) extends Resource with Ordered with Prioritized {

  override def toString() = {
    super.toString + "'%s' '%s'" format (title, description)
  }

  lazy val sprints = SprintRepo.findByProject(this)
}

trait ProjectComponent extends ResourceComponent {
  self: Profile =>

  object Projects extends Resources[Project]("projects") {
    def title = column[String]("title")
    def description = column[String]("description", O.DBType("VARCHAR(4096)"))
    def status = column[Int]("status")
    def order = column[Int]("order")
    def priority = column[Int]("priority")
    def * = id.? ~ created_time ~ updated_time ~ title ~ description ~ status ~ order ~ priority <> (Project, Project.unapply _)
  }
}

object ProjectStatus extends Enumeration {
  val PLANNING = Value(1)
  val ACTIVE = Value(2)
  val RESOLVED = Value(3)
  val PENDING = Value(4)
  val BLOCKED = Value(5)
}