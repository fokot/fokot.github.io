import Dependencies._

ThisBuild / scalaVersion     := "2.12.10"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.fokot"
ThisBuild / organizationName := "fokot"


lazy val root = (project in file("."))
  .settings(
    name := "fokot.github.io",
    libraryDependencies ++= Seq(osLib, scalatexSite),
    scalatex.SbtPlugin.projectSettings
  )
