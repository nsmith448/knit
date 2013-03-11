package repositories

import models.{ Sprint, Story }
import models.AppDB._
import play.api._
import play.api.Play.current
import slick.session.Session

object StoryRepo extends ResourceRepo[Story, dal.Stories.type](dal.Stories) {

  import dal.profile.simple._

  def t_bySprint = for {
    id <- Parameters[Long]
    st <- table if st.sprint_id === id
  } yield st

  def findBySprint(id: Long): List[Story] = {
    database withSession {
      implicit session: Session =>
        t_bySprint(id).list
    }
  }
}