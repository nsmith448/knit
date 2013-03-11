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

object SprintsFixture extends Fixture {
  def createFixtures = {

    val now = new java.util.Date()
    
    SprintRepo.createAll(
      //     Id    Created               Updated                     Project  Title                     Description  Begin Date   End Date     Status  Order  Priority
      Sprint(None, "2/10/2013 11:41:13", new Timestamp(now getTime), 1L,      "Administrator Controls", filler(2),   "3/3/2013",  "3/10/2013", 100,    1,     Priority.NORMAL),
      Sprint(None, "2/10/2013 11:42:58", new Timestamp(now getTime), 1L,      "Developer Controls",     filler(3),   "3/10/2013", "3/15/2013", 200,    2,     Priority.NORMAL),
      Sprint(None, "2/10/2013 11:41:13", new Timestamp(now getTime), 1L,      "Administrator Controls", filler(2),   "3/18/2013", "3/22/2013", 100,    1,     Priority.NORMAL),
      Sprint(None, "2/10/2013 11:41:13", new Timestamp(now getTime), 1L,      "Researching Next Steps", filler(1),   "3/10/2013", "3/15/2013", 100,    1,     Priority.NORMAL),
      Sprint(None, "2/10/2013 11:41:13", new Timestamp(now getTime), 2L,      "Some Future Sprint",     filler(4),   "4/3/2013",  "4/10/2013", 100,    1,     Priority.NORMAL),
      Sprint(None, "2/10/2013 11:41:13", new Timestamp(now getTime), 1L,      "Another Future Sprint",  filler(2),   "5/3/2013",  "5/10/2013", 100,    1,     Priority.NORMAL))
  }
}