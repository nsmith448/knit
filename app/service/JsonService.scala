package service

import models._
import repositories._
import play.api.libs.json.Json.toJson
import util.JsonConversions._

object JsonService {

  def project(id: Long) = {
    val project = ProjectRepo findById id
    toJson(project)
  }

  def projects() = {
    val projects = ProjectRepo.all
    toJson(projects)
  }

  def sprint(id: Long) = {
    val sprint = SprintRepo findById id
    toJson(sprint)
  }

  def project_sprints(id: Long) = {
    val sprints = SprintRepo findByProject id
    toJson(sprints)
  }

  def story(id: Long) = {
    val story = StoryRepo findById id
    toJson(story)
  }

  def sprint_stories(id: Long) = {
    val stories = StoryRepo findBySprint id
    toJson(stories)
  }
}