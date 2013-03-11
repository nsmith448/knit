import play.api.db.DB
import play.api.GlobalSettings
import play.api.Application
import slick.session.Session
import models._
import fixtures._
import play.Logger.{ debug, info, warn, error }

object Global extends GlobalSettings with SlickDriven {

  override def onStart(app: Application) {
    implicit val application = app

    lazy val database = getDb
    lazy val dal = getDal
    database.withSession {
      implicit session: Session =>
        if (isSql)
          dal.drop
        dal.create
    }
  }
}