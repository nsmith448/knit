package util

import java.text.SimpleDateFormat
import java.sql.{ Date, Timestamp }

object TimeDateConversions {

  /**
   * Some helpers for constructing java.sql.Date's
   */
  lazy val dateParser = new SimpleDateFormat("M/d/yyyy")
  implicit def utilToSqlDate(d: java.util.Date): java.sql.Date = new java.sql.Date(d.getTime)
  implicit def toSqlDate(s: String): java.sql.Date = dateParser parse s

  /**
   * Some helpers for constructing java.sql.Timestamp's
   */
  lazy val timeParser = new SimpleDateFormat("M/d/yyyy H:m:ss")
  implicit def utilToSqlTimestamp(t: java.util.Date): java.sql.Timestamp = new java.sql.Timestamp(t.getTime)
  implicit def toSqlTimestamp(s: String): java.sql.Timestamp = timeParser parse s
}