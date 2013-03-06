package fixtures

import models.DAL
import models.Project
import util.Ipsum._
import util.TimeDateConversions._
import models.AppDB._
import java.sql.Timestamp
import slick.session.Session

object ProjectsFixture {
  def createFixtures(dal: DAL) = {

    database withSession {
      implicit session: Session =>

        dal.Projects.insertRows(
          //      Id    Created               Updated              Title           Description  Status
          Project(None, "2/2/2013 9:03:23",  "2/8/2013 5:33:512", "Test Project", filler(2),   100),
          Project(None, "2/3/2013 10:17:21", "2/13/2013 6:18:288", "Project 2",   filler(2),   100))
    }
  }
}