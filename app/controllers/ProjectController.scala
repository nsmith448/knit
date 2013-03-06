package controllers

import play.api._
import play.api.mvc._
import models.AppDB._
import slick.session.Session

object ProjectController extends Controller {

  def list = Action {
    database.withSession {
      implicit session: Session =>
        val pr = dal.Projects all ()
        Ok(views.html.Projects.list(pr))
    }
  }

  def view(id: Long) = Action {
    database.withSession {
      implicit session: Session =>
        val pr = dal.Projects.findById(id).get
        Ok(views.html.Projects.view(pr))
    }
  }

}