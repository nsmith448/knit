package models

import java.sql.{ Date, Timestamp }
import repositories.StoryRepo
import AppDB._

case class Sprint(
  id: Option[Long],
  created_time: Timestamp,
  updated_time: Timestamp,
  project_id: Long,
  title: String,
  description: String,
  begin_date: Date,
  end_date: Date,
  status: Int,
  order: Int,
  priority: Int) extends Resource with Ordered with Prioritized {
}

trait SprintComponent extends ResourceComponent with OrderedComponent {
  this: Profile =>

  import profile.simple._

  object Sprints extends Resources[Sprint]("sprints") with OrderedRows[Sprint] {
    def project_id = column[Long]("project_id")
    def project_fk = foreignKey("project_fk", project_id, dal.Projects)(_.id)
    def title = column[String]("title")
    def description = column[String]("description", O.DBType("VARCHAR(4096)"))
    def begin_date = column[Date]("begin_date")
    def end_date = column[Date]("end_date")
    def status = column[Int]("status")
    def priority = column[Int]("priority")
    def * = id.? ~ created_time ~ updated_time ~ project_id ~ title ~ description ~ begin_date ~ end_date ~ status ~ order ~ priority <> (Sprint, Sprint.unapply _)
  }
}