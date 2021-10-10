name := "HoursLog"

version := "1.0"

lazy val flywaymod = RootProject(file("flyway-mod"))

lazy val `hourslog` = (project in file(".")).dependsOn(flywaymod)
  .enablePlugins(
    SwaggerPlugin,
    FlywayPlugin,
    PlayScala
  )

swaggerDomainNameSpaces := Seq("models")

val slickVersion = "3.3.3"

resolvers += "Akka Snapshot Repository" at "https://repo.akka.io/snapshots/"

scalaVersion := "2.13.5"

libraryDependencies ++= Seq(guice,
  "io.monix" %% "monix" % "3.4.0",
  "org.webjars" % "swagger-ui" % "3.52.3",

  "com.typesafe.slick" %% "slick" % slickVersion,
  "com.typesafe.slick" %% "slick-hikaricp" % slickVersion,
  "com.typesafe.slick" %% "slick-codegen" % slickVersion,

  "com.typesafe.play" %% "play-slick" % "5.0.0",
  "com.typesafe.play" %% "play-slick-evolutions" % "5.0.0",

  "org.flywaydb" % "flyway-core" % "7.15.0",

  "org.postgresql" % "postgresql" % "42.2.24",

  "org.scalatestplus.play" %% "scalatestplus-play" % "5.1.0" % Test,
  "org.scalamock" %% "scalamock" % "5.1.0" % Test
)
      