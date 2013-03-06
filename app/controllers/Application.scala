package controllers

import play.api._
import play.api.mvc._
//import models.{ Project, Projects }

object Application extends Controller {

  def index = Action {
    /*
    database withSession {
      //val pr = Query(Projects) list
      

      Ok(views.html.index("Welcome to Play!"))
    }*/
    Ok
  }

}