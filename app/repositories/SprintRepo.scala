package repositories

import models.{ Sprint, Project }
import models.AppDB._
import play.api._
import play.api.Play.current
import slick.session.Session

object SprintRepo extends ResourceRepo[Sprint, dal.Sprints.type](dal.Sprints) {

  import dal.profile.simple._

  def t_byProject = for {
    id <- Parameters[Long]
    sp <- table if sp.project_id === id
  } yield sp

  def findByProject(id: Long): List[Sprint] = {
    database withSession {
      implicit session: Session =>
        t_byProject(id).list
    }
  }
}