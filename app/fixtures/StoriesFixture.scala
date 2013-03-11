package fixtures

import java.sql.Timestamp

import models.DAL
import models.Priority
import models.Story
import repositories.StoryRepo
import util.TimeDateConversions.toSqlTimestamp

object StoriesFixture extends Fixture {
  def createFixtures = {

    val now = new java.util.Date()

    val asAdmin = "As an administrator, I want to "
    val asDev = "As a developer, I want to "

    StoryRepo.createAll(
      //    Id    Created                     Updated               Sprint   Title              Description                         Status  Order  Priority         Points  Assignee
      Story(None, "2/10/2013 11:44:34", new Timestamp(now getTime), 1L,      "Create Projects", asAdmin + "create a new project",   100,    1,     Priority.NORMAL, 2,      None),
      Story(None, "2/10/2013 11:45:45", new Timestamp(now getTime), 1L,      "Create Sprints",  asAdmin + "create a new sprint",    200,    2,     Priority.NORMAL, 2,      None),
      Story(None, "2/10/2013 11:45:10", new Timestamp(now getTime), 1L,      "Create Stories",  asAdmin + "create a new story",     100,    4,     Priority.HIGH,   2,      Some(1)),
      Story(None, "2/10/2013 11:46:50", new Timestamp(now getTime), 1L,      "Create Tasks",    asAdmin + "create a new task",      200,    3,     Priority.NORMAL, 3,      Some(2)),
      Story(None, "2/10/2013 11:50:54", new Timestamp(now getTime), 2L,      "Create Tasks",    asDev + "create a new task",        100,    1,     Priority.NORMAL, 1,      None),
      Story(None, "2/10/2013 11:52:45", new Timestamp(now getTime), 2L,      "Assign Task",     asDev + "assign a task to myself",  200,    2,     Priority.NORMAL, 2,      None))
  }
}