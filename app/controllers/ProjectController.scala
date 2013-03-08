package controllers

import play.api._
import play.api.mvc._
import models.AppDB._
import slick.session.Session
import repositories.ProjectRepo

object ProjectController extends Controller {

  def list = Action {
    val projects = ProjectRepo.all
    Ok(views.html.Projects.list(projects))
  }

  def view(id: Long) = Action {
    val pr = (ProjectRepo findById id).get
    Ok(views.html.Projects.view(pr))
  }

}