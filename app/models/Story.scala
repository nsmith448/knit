package models

import java.sql.Timestamp
import slick.session.Session
import AppDB._
import repositories.TaskRepo

case class Story(
  id: Option[Long],
  created_time: Timestamp,
  updated_time: Timestamp,
  sprint_id: Long,
  title: String,
  description: String,
  status: Int,
  order: Int,
  priority: Int,
  points: Int,
  assignee_id: Option[Long]) extends Resource with Ordered with Prioritized {

  lazy val tasks = TaskRepo findByStory id
}

trait StoryComponent extends ResourceComponent with OrderedComponent {
  this: Profile =>

  import profile.simple._

  object Stories extends Resources[Story]("stories") with OrderedRows[Story] {
    def sprint_id = column[Long]("sprint_id")
    def sprint_fk = foreignKey("sprint_fk", sprint_id, dal.Sprints)(_.id)
    def title = column[String]("title")
    def description = column[String]("description", O.DBType("VARCHAR(4096)"))
    def status = column[Int]("status")
    def priority = column[Int]("priority")
    def points = column[Int]("points")
    def assignee_id = column[Option[Long]]("assignee_id")
    def assigneee_fk = foreignKey("assignee_fk", assignee_id, dal.Users)(_.id.?)
    def * = id.? ~ created_time ~ updated_time ~ sprint_id ~ title ~ description ~ status ~ order ~ priority ~ points ~ assignee_id <> (Story, Story.unapply _)
  }
}

object StoryStatus extends Enumeration {
  val NEW = Value(1)
  val IN_PROGRESS = Value(2)
  val RESOLVED = Value(3)
  val BLOCKED = Value(4)
}