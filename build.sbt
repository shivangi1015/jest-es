name := "Jest-ES"

version := "0.1"

scalaVersion := "2.12.4"

val ES_VERSION = "5.3.3"
val jestClient = "io.searchbox" % "jest" % ES_VERSION
val playJson = "com.typesafe.play" %% "play-json" % "2.6.8"

libraryDependencies ++= Seq(
  jestClient,
  playJson
)