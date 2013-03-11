import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         	= "Knit"
  val appVersion      	= "1.0-SNAPSHOT"
  val slick			  	= "com.typesafe.slick" %% "slick" % "1.0.0"
  val mysql				= "mysql" % "mysql-connector-java" % "5.1.23"
  val webjars           = "org.webjars" % "webjars-play" % "2.1.0"
  val jquery            = "org.webjars" % "jquery" % "1.9.1"
  val bootstrap         = "org.webjars" % "bootstrap" % "2.3.1"
  val angular           = "org.webjars" % "angularjs" % "1.1.3"
  val angularui         = "org.webjars" % "angular-ui" % "0.3.2-1"
  val angularstrap      = "org.webjars" % "angular-strap" % "0.6.6"
  
  val appDependencies = Seq(
    // Add your project dependencies here,
    jdbc,
    mysql,
    slick,
    webjars,
    jquery,
    bootstrap,
    angular,
    angularui,
    angularstrap
  )


  val main = play.Project(appName, appVersion, appDependencies).settings(
    // Add your own project settings here
  )

}
