package fixtures

import java.sql.Timestamp

import models.DAL
import models.Priority
import models.Sprint
import repositories.ProjectRepo
import repositories.SprintRepo
import util.Ipsum.filler
import util.TimeDateConversions.toSqlDate
import util.TimeDateConversions.toSqlTimestamp

object SprintsFixture {
  def createFixtures = {

    val now = new java.util.Date()

    val testProject = ProjectRepo.findById(1L).get
    val proj_id = testProject.id.get

    SprintRepo.createAll(
      //     Id    Created                Updated                     Project  Title                     Description  Begin Date   End Date    Status  Order  Priority
      Sprint(None, "2/10/2013 11:41:13", new Timestamp(now getTime), proj_id, "Administrator Controls", filler(2),   "2/11/2013", "2/15/2013", 100,    1,     Priority.NORMAL),
      Sprint(None, "2/10/2013 11:42:58", new Timestamp(now getTime), proj_id, "Developer Controls",     filler(2),   "2/18/2013", "2/22/2013", 200,    2,     Priority.NORMAL))
  }
}