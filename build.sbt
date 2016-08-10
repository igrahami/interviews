lazy val commonSettings = Seq(
  scalaVersion := "2.11.8",
  organization := "com.iangraham"
)

name := "address-book"

version := "0.1"

lazy val testDependencies = Seq("info.cukes" %% "cucumber-scala" % "1.2.4" % "it",
  "info.cukes" % "cucumber-junit" % "1.2.4"  % "it",
  "junit" % "junit" % "4.12"  % "it,test",
  "org.scalactic" %% "scalactic" % "3.0.0",
  "org.scalatest" %% "scalatest" % "3.0.0" % "test"
)

lazy val `address-book` = (project in file(".")).
  configs(IntegrationTest).
  settings(commonSettings: _*).
  settings(Defaults.itSettings: _*).
  settings(
    libraryDependencies ++= testDependencies
  )