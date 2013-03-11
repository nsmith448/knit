package repositories

import models.{ Task, Story }
import models.AppDB._
import play.api._
import play.api.Play.current
import slick.session.Session

object TaskRepo extends ResourceRepo[Task, dal.Tasks.type](dal.Tasks) {

  import dal.profile.simple._

  protected def t_byStory = (for {
    id <- Parameters[Long]
    tk <- table if tk.story_id === id
  } yield tk)

  /**
   * List all tasks in a given story
   */
  def findByStory(id: Option[Long]): List[Task] = {
    database withSession {
      implicit session: Session =>
        id.fold(List[Task]())(t_byStory(_) list)
    }
  }
}