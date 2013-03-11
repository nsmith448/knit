package util

import play.api.libs.json._
import play.api.libs.functional.syntax._
import models._
import java.sql.Timestamp
import java.sql.Date
import TimeDateConversions._

object JsonConversions {

  implicit val formatTimestamp = new Format[Timestamp] {
    // Write Timestamp to JSON Number
    def writes(ts: Timestamp): JsValue = JsNumber(ts.getTime)

    // Read JSON Number to Timestamp
    def reads(ts: JsValue): JsResult[Timestamp] = JsSuccess(new Timestamp(ts.as[Long]))
  }

  implicit val projectReads = Json.format[Project]
  implicit val projectWrites = Json.writes[Project]

  implicit val storyReads = Json.format[Story]
  implicit val storyWrites = Json.writes[Story]

  implicit val sprintReads = Json.format[Sprint]
  implicit val sprintWrites = Json.writes[Sprint]

  implicit val taskReads = Json.format[Task]
  implicit val taskWrites = Json.writes[Task]

  /*
  implicit val sprintWrites = (
    (__ \ "id").write[Option[Long]] ~
    (__ \ "created_time").write[Timestamp] ~
    (__ \ "updated_time").write[Timestamp] ~
    (__ \ "project_id").write[Long] ~
    (__ \ "title").write[String] ~
    (__ \ "description").write[String] ~
    (__ \ "begin_date").write[Date] ~
    (__ \ "end_date").write[Date] ~
    (__ \ "status").write[Int] ~
    (__ \ "order").write[Int] ~
    (__ \ "priority").write[Int])(unlift(Sprint.unapply))
    */
}