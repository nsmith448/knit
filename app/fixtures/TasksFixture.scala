package fixtures

import models.{ Sprint, Story, DAL }
import slick.session.Session
import java.sql.{ Date, Timestamp }
import repositories.SprintRepo
import repositories.StoryRepo
import repositories.TaskRepo
import models.Task
import util.TimeDateConversions.toSqlTimestamp
import models.Priority

object TasksFixture extends Fixture {
  def createFixtures = {
    
    TaskRepo.createAll(
      //   Id    Created               Updated               Story  Title                      Description                 Status  Order  Priority         Assignee  Total  Spent
      Task(None, "2/10/2013 11:55:25", "2/10/2013 12:34:13", 1L,    "Implement Projects Page", "Make sure to do it right", 200,    3,     Priority.NORMAL, 1L,       3.0f,   1.0f),
      Task(None, "2/10/2013 11:55:25", "2/10/2013 12:34:14", 2L,    "Implement Sprints Page",  "Make sure to do it right", 200,    4,     Priority.LOW,    2L,       2.0f,   0f),
      Task(None, "2/10/2013 11:55:25", "2/10/2013 12:34:16", 3L,    "Implement Stories Page",  "Make sure to do it right", 300,    2,     Priority.NORMAL, 1L,       3.0f,   2.0f),
      Task(None, "2/10/2013 11:55:25", "2/10/2013 12:34:17", 4L,    "Implement Tasks Page",    "Make sure to do it right", 100,    1,     Priority.HIGH,   2L,       5.0f,   5.0f),
      Task(None, "2/10/2013 11:56:15", "2/10/2013 12:34:19", 4L,    "Test Tasks Page",         "Make sure to do it right", 100,    5,     Priority.NORMAL, 1L,       3.0f,   0f)
    )
  }
}