package fixtures

import util.Ipsum._
import util.TimeDateConversions._
import models.AppDB._
import models.{ Sprint, Project, DAL }
import slick.session.Session
import java.sql.{ Date, Timestamp }
import models.Priority

object SprintsFixture {
  def createFixtures(dal: DAL) = {

    val now = new java.util.Date()

    database withSession {
      implicit session: Session =>

        val testProject = dal.Projects.findById(1L).get
        val proj_id = testProject.id.get

        dal.Sprints.insertRows(
          //     Id    Created                Updated                     Project  Title                     Description  Begin Date   End Date    Status  Order  Priority
          Sprint(None, "2/10/2013 11:41:13", new Timestamp(now getTime), proj_id, "Administrator Controls", filler(2),   "2/11/2013", "2/15/2013", 100,    1,     Priority.NORMAL),
          Sprint(None, "2/10/2013 11:42:58", new Timestamp(now getTime), proj_id, "Developer Controls",     filler(2),   "2/18/2013", "2/22/2013", 200,    2,     Priority.NORMAL))
    }
  }
}