package controllers

import play.api._
import play.api.mvc._
import models.{ Story, DAL }

object StoryController extends Controller {

  /*
  def list = Action {
    val st = StoryRepo all()
    Ok(views.html.Stories.list(st))
  }
  */

  def view(id: Long) = Action {
    /*
    val st = StoryRepo.findById(id).get
    Ok(views.html.Stories.view(st))
    */
    Ok
  }

}