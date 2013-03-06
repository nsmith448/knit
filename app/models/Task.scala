package models

import java.sql.{ Date, Timestamp }
import slick.session.Session

case class Task(
  id: Option[Long],
  created_time: Timestamp,
  updated_time: Timestamp,
  story_id: Long,
  title: String,
  description: String,
  status: Int,
  assignee_id: Long,
  time_total: Float,
  time_spent: Float) extends Resource with Trackable {

  override def tracked(total: Float, spent: Float) = {
    this.copy(time_total = total, time_spent = spent)
  }
}

trait TaskComponent extends ResourceComponent {
  this: Profile =>

  import profile.simple._

  object Tasks extends Resources[Task]("tasks") {
    def story_id = column[Long]("story_id")
    //def story_fk = foreignKey("story_fk", story_id, Stories)(_.id)
    def title = column[String]("title")
    def description = column[String]("description", O.DBType("VARCHAR(4096)"))
    def status = column[Int]("status")
    def assignee_id = column[Long]("assignee_id")
    def time_total = column[Float]("time_total")
    def time_spent = column[Float]("time_spent")
    def * = id.? ~ created_time ~ updated_time ~ story_id ~ title ~ description ~ status ~ assignee_id ~ time_total ~ time_spent <> (Task, Task.unapply _)

    def findByStory(story: Story)(implicit session: Session) = {
      (for {
        tk <- this if tk.story_id === story.id
      } yield tk) list
    }
  }
}