package controllers

import play.api._
import play.api.mvc._
import service.JsonService
import util.JsonConversions._
import play.api.libs.json.Json.toJson

object Application extends Controller {

  def index = Action {
    Ok(views.html.index("{}"))
  }

  def indexId(id: Long) = Action {
    val proj = JsonService.project(id)
    Ok(views.html.index(proj.toString))
  }

  def javascriptRoutes = Action { implicit request =>
    import routes.javascript._
    Ok(
      Routes.javascriptRouter("jsRoutes")(
        ProjectController.byId)).as("text/javascript")
  }

  def projectTemplate = Action {
    Ok(views.html.templates.project())
  }
}