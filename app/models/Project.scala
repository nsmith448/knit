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
  status: Int) extends Resource {

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
    def * = id.? ~ created_time ~ updated_time ~ title ~ description ~ status <> (Project, Project.unapply _)
  }
}