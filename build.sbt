name := "scala-course-1"

version := "0.1"

scalaVersion := "2.13.10"

libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.14" % "test"

libraryDependencies += "org.scalameta" %% "munit" % "0.7.22" % Test

// used for submitting the assignments to Coursera
libraryDependencies +=  "org.scalaj" %% "scalaj-http" % "2.4.2"

testFrameworks += new TestFramework("munit.Framework")