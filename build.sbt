name := "HoursLog"
 
version := "1.0"

lazy val flywaymod = RootProject(file("flyway-mod"))
      
lazy val `hourslog` = (project in file(".")).enablePlugins(PlayScala)
         
resolvers += "Akka Snapshot Repository" at "https://repo.akka.io/snapshots/"
      
scalaVersion := "2.13.5"

libraryDependencies ++= Seq( jdbc , ehcache , ws , specs2 % Test , guice )
      