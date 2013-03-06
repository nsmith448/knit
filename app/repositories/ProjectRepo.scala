package repositories

import models.Project
import models.AppDB._
import play.api._
import play.api.Play.current

object ProjectRepo extends ResourceRepo[Project](dal.Projects) {

}