import Dependencies._

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "com.example",
      scalaVersion := "2.12.2",
      version      := "0.1.0-SNAPSHOT"
    )),
    name := "Hello",
    libraryDependencies ++= Seq(scalaTest % Test,
                                "org.apache.hbase" % "hbase-common" % "1.3.1",
				"org.apache.hadoop" % "hadoop-common" % "2.8.0",
                                "org.apache.hbase" % "hbase-client" % "1.3.1"),
    assemblyMergeStrategy in assembly := {
      case PathList("javax", "servlet", xs @ _*)         => MergeStrategy.first
      case PathList(ps @ _*) if ps.last endsWith ".properties" => MergeStrategy.first
      case PathList(ps @ _*) if ps.last endsWith ".xml" => MergeStrategy.first
      case PathList(ps @ _*) if ps.last endsWith ".types" => MergeStrategy.first
      case PathList(ps @ _*) if ps.last endsWith ".class" => MergeStrategy.first
      case "application.conf"                            => MergeStrategy.concat
      case "unwanted.txt"                                => MergeStrategy.discard
      case x =>
        val oldStrategy = (assemblyMergeStrategy in assembly).value
        oldStrategy(x)
    }
  )
