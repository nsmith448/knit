package controllers

import play.api._
import play.api.mvc._
import models.Task
import repositories.SprintRepo
import play.api.libs.json.Json
import util.JsonConversions._

object TaskController extends Controller {

  def list = Action {
    Ok
  }

  def json(id: Long) = Action {
    val sprint = SprintRepo findById id
    Ok(Json.toJson(sprint))
  }

}