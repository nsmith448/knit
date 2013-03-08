package fixtures

import models.{ Sprint, Story, DAL }
import slick.session.Session
import java.sql.{ Date, Timestamp }

object TasksFixture {
  def createFixtures = {

    val now = new java.util.Date()
    /*
    val sprint1 = dal.Sprints findById (1L) get
    val sprint2 = dal.Sprints findById (2L) get

    val asAdmin = "As an administrator, I want to "
    val asDev = "As a developer, I want to "

    dal.Stories.insertAll(
      //    Id    Created                     Updated                     Sprint          Title              Description                      Status  Points  Assignee
      Story(None, new Timestamp(now getTime), new Timestamp(now getTime), sprint1.id get, "Create Projects", asAdmin + "create a new project", 100, 2, None),
      Story(None, new Timestamp(now getTime), new Timestamp(now getTime), sprint1.id get, "Create Sprints", asAdmin + "create a new sprint", 200, 2, None),
      Story(None, new Timestamp(now getTime), new Timestamp(now getTime), sprint1.id get, "Create Stories", asAdmin + "create a new story", 100, 2, Some(1)),
      Story(None, new Timestamp(now getTime), new Timestamp(now getTime), sprint1.id get, "Create Tasks", asAdmin + "create a new task", 200, 3, Some(2)),
      Story(None, new Timestamp(now getTime), new Timestamp(now getTime), sprint2.id get, "Create Tasks", asDev + "create a new task", 100, 1, None),
      Story(None, new Timestamp(now getTime), new Timestamp(now getTime), sprint2.id get, "Assign Task", asDev + "assign a task to myself", 200, 2, None))
      */
  }
}