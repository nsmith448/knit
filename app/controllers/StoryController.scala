package controllers

import play.api._
import play.api.mvc._
import models.Story
import service.JsonService

object StoryController extends Controller {

  def byId(id: Long) = Action {
    Ok(JsonService.story(id))
  }

  def bySprint(id: Long) = Action {
    Ok(JsonService.sprint_stories(id))
  }
}