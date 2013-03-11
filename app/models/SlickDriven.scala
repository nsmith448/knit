package models

import slick.session.Database
import play.api.db.DB
import play.api.Application
import slick.driver.ExtendedProfile

trait SlickDriven {

  val SLICK_DRIVER = "slick.db.driver"
  val DEFAULT_SLICK_DRIVER = "scala.slick.driver.H2Driver"
  var isSql = false

  /**
   * Loads the proper database driver based on play configuration
   */
  def getDal(implicit app: Application): DAL = {
    val driverClass = app.configuration.getString(SLICK_DRIVER).getOrElse(DEFAULT_SLICK_DRIVER)
    val driver = singleton[ExtendedProfile](driverClass)
    isSql = driverClass contains "SQL"
    new DAL(driver)
  }

  def getDb(implicit app: Application) = {
    Database.forDataSource(DB.getDataSource())
  }

  private def singleton[T](name: String)(implicit man: Manifest[T]): T =
    Class.forName(name + "$").getField("MODULE$").get(man.runtimeClass).asInstanceOf[T]

}