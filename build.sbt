name         in ThisBuild := "rtb"
version      in ThisBuild := "0.0.1"
organization in ThisBuild := "underscoreio"
scalaVersion in ThisBuild := "2.11.8"

scalacOptions in ThisBuild ++= Seq("-feature", "-Xfatal-warnings", "-deprecation", "-unchecked", "-Ywarn-unused-import")


scalacOptions in (Compile, console) := Seq("-feature", "-Xfatal-warnings", "-deprecation", "-unchecked")

licenses += ("Apache-2.0", url("http://apache.org/licenses/LICENSE-2.0"))

resolvers += Resolver.sonatypeRepo("snapshots")

libraryDependencies ++= Seq(
  "com.github.finagle" %% "finch-core" % "0.11.0-M2",
  "com.github.finagle" %% "finch-circe" % "0.11.0-M2",
  "io.circe" %% "circe-core" % "0.4.1",
  "io.circe" %% "circe-generic" % "0.4.1",
  "io.circe" %% "circe-parser" % "0.4.1",
  "org.scalatest" %% "scalatest" % "2.2.6" % "test",
  "org.scalacheck" %% "scalacheck" % "1.12.5" % "test"
)

initialCommands in console := """
 |rtb.Rtb.server
""".trim.stripMargin

cleanupCommands in console := """
 |rtb.Rtb.server.close()
""".trim.stripMargin
