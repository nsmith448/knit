package controllers

import play.api._
import play.api.mvc._
import models.{ Sprint, DAL }

object SprintController extends Controller {

  def list = Action {
    /*
    database withSession {
      val pr = Query(Sprints) list

      Ok(views.html.Sprints.list(pr.map(_.changed)))
    }*/
    Ok
  }

}