
import complete.DefaultParsers.spaceDelimited
import Dependencies._

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "com.example",
      scalaVersion := "2.12.1",
      version      := "0.1.0-SNAPSHOT"
    )),
    name := "Hello",
    libraryDependencies += scalaTest % Test,
    sampleTask := {
        println("hello")
        "Hello world"
    },
    anotherSampleTask := {
        sampleTask.value.map(_.toInt)
    },
    thirdSampleTask := {
        val Seq(firstArg, secondArg) = spaceDelimited("<arg> <arg>").parsed
        println(sampleTask.value)
        println(anotherSampleTask.value)
        println(firstArg, secondArg)
    }
  )

val sampleTask = taskKey[String]("A task that produces a string")

val anotherSampleTask = taskKey[Seq[Int]]("A task that produces a sequence of ints")

val thirdSampleTask = inputKey[Unit]("A task that prints out the result of the first two tasks along with its two arguments")

