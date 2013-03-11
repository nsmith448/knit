package controllers

import play.api._
import play.api.mvc._
import models.AppDB._
import slick.session.Session
import repositories.ProjectRepo
import service.JsonService

object ProjectController extends Controller {

  def list = Action {
    Ok(JsonService.projects())
  }

  def byId(id: Long) = Action {
    Ok(JsonService.project(id))
  }
}