package controllers

import play.api._
import play.api.mvc._
import models.Sprint
import service.JsonService

object SprintController extends Controller {

  def byId(id: Long) = Action {
    Ok(JsonService.sprint(id))
  }

  def byProject(id: Long) = Action {
    Ok(JsonService.project_sprints(id))
  }
}