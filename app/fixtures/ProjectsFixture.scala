package fixtures

import models.DAL
import models.Project
import util.Ipsum._
import util.TimeDateConversions._
import repositories.ProjectRepo
import java.sql.Timestamp
import slick.session.Session
import models.Priority

object ProjectsFixture extends Fixture {
  def createFixtures = {

    ProjectRepo.createAll(
      //      Id    Created               Updated              Title           Description  Status  Order  Priority
      Project(None, "2/2/2013 9:03:23",  "2/8/2013 5:33:512", "Test Project", filler(2),   100,     1,     Priority.NORMAL),
      Project(None, "2/3/2013 10:17:21", "2/13/2013 6:18:288", "Project 2",   filler(2),   100,     2,     Priority.LOW))
  }
}