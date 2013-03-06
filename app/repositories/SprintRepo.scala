package repositories

import models.{ Sprint, Project }
import models.AppDB._
import play.api._
import play.api.Play.current
import slick.session.Session

object SprintRepo extends ResourceRepo[Sprint](dal.Sprints) {

  def findByProject(proj: Project): List[Sprint] = {
    database withSession {
      implicit session: Session =>
        dal.Sprints.findByProject(proj)
    }
  }
}