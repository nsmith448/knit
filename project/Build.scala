import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         	= "TrackIt"
  val appVersion      	= "1.0-SNAPSHOT"
  val slick			  	= "com.typesafe.slick" %% "slick" % "1.0.0"
  val mysql				= "mysql" % "mysql-connector-java" % "5.1.23"

    
  val appDependencies = Seq(
    // Add your project dependencies here,
    jdbc,
    mysql,
    slick
  )


  val main = play.Project(appName, appVersion, appDependencies).settings(
    // Add your own project settings here
  )

}
