package models

import java.sql.{ Date, Timestamp }
import slick.driver.H2Driver._
import slick.session.Session
import repositories.StoryRepo

case class Sprint(
  id: Option[Long],
  created_time: Timestamp,
  updated_time: Timestamp,
  project_id: Long,
  title: String,
  description: String,
  begin_date: Date,
  end_date: Date,
  status: Int) extends Resource {

  lazy val stories = StoryRepo.findBySprint(this)
}

trait SprintComponent extends ResourceComponent {
  this: Profile =>

  import profile.simple._

  object Sprints extends Resources[Sprint]("sprints") {
    def project_id = column[Long]("project_id")
    //def project_fk = foreignKey("project_fk", project_id, Projects)(_.id)
    def title = column[String]("title")
    def description = column[String]("description", O.DBType("VARCHAR(4096)"))
    def begin_date = column[Date]("begin_date")
    def end_date = column[Date]("end_date")
    def status = column[Int]("status")
    def * = id.? ~ created_time ~ updated_time ~ project_id ~ title ~ description ~ begin_date ~ end_date ~ status <> (Sprint, Sprint.unapply _)

    def findByProject(proj: Project)(implicit session: Session) = {
      (for {
        sp <- this if sp.project_id === proj.id
      } yield sp) list
    }
  }
}