package repositories

import models.{ Sprint, Story }
import models.AppDB._
import play.api._
import play.api.Play.current
import slick.session.Session

object StoryRepo extends ResourceRepo[Story](dal.Stories) {

  def findBySprint(sprint: Sprint): List[Story] = {
    database withSession {
      implicit session: Session =>
        dal.Stories.findBySprint(sprint)
    }
  }
}