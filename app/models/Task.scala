package models

import java.sql.{ Date, Timestamp }
import AppDB._

case class Task(
  id: Option[Long],
  created_time: Timestamp,
  updated_time: Timestamp,
  story_id: Long,
  title: String,
  description: String,
  status: Int,
  order: Int,
  priority: Int,
  assignee_id: Long,
  time_total: Float,
  time_spent: Float) extends Resource with Trackable with Ordered with Prioritized {

  override def tracked(total: Float, spent: Float) = {
    this.copy(time_total = total, time_spent = spent)
  }
}

trait TaskComponent extends ResourceComponent with OrderedComponent {
  this: Profile =>

  import profile.simple._

  object Tasks extends Resources[Task]("tasks") with OrderedRows[Task] {
    def story_id = column[Long]("story_id")
    def story_fk = foreignKey("story_fk", story_id, dal.Stories)(_.id)
    def title = column[String]("title")
    def description = column[String]("description", O.DBType("VARCHAR(4096)"))
    def status = column[Int]("status")
    def priority = column[Int]("priority")
    def assignee_id = column[Long]("assignee_id")
    def time_total = column[Float]("time_total")
    def time_spent = column[Float]("time_spent")
    def * = id.? ~ created_time ~ updated_time ~ story_id ~ title ~ description ~ status ~ order ~ priority ~ assignee_id ~ time_total ~ time_spent <> (Task, Task.unapply _)
  }
}

object TaskStatus extends Enumeration {
  val NEW = Value(1)
  val IN_PROGRESS = Value(2)
  val RESOLVED = Value(3)
  val BLOCKED = Value(4)
}