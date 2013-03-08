package models

import slick.session.Database
import slick.driver.ExtendedProfile
import play.api.db.DB
import play.api.Application
import fixtures._
import play.Logger.{ debug, info, warn, error }

/**
 * Use Cake pattern to inject proper database driver
 */
class DAL(override val profile: ExtendedProfile)
  extends ProjectComponent
  with SprintComponent
  with StoryComponent
  with TaskComponent
  with UserComponent
  with Profile {

  import profile.simple._

  private def ddl = { Projects.ddl ++ Users.ddl ++ Sprints.ddl ++ Stories.ddl ++ Tasks.ddl }

  /**
   * Create the database schema
   */
  def create(implicit session: Session): Unit = {
    info("Creating Database Schema:" + ddl.createStatements.fold()(_ + "\n" + _))
    ddl.create

    // Insert test data
    UsersFixture.createFixtures
    ProjectsFixture.createFixtures
    SprintsFixture.createFixtures
    StoriesFixture.createFixtures
    TasksFixture.createFixtures
  }

  /**
   * Delete the database schema
   */
  def drop(implicit session: Session): Unit = {
    info("Dropping Database Schema:" + ddl.dropStatements.fold()(_ + "\n" + _))
    ddl.drop
  }
}

import play.api.Play.current

/**
 * Glue that binds the database driver to the DAL
 */
object AppDB extends SlickDriven {

  lazy val database = getDb
  lazy val dal = getDal

}