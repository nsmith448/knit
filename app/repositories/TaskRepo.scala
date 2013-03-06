package repositories

import models.{ Task, Story }
import models.AppDB._
import play.api._
import play.api.Play.current
import slick.session.Session

object TaskRepo extends ResourceRepo[Task](dal.Tasks) {

  def findByStory(story: Story): List[Task] = {
    database withSession {
      implicit session: Session =>
        dal.Tasks.findByStory(story)
    }
  }
}