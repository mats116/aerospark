name := "aerospike-spark"

version := "1.1.6"

organization := "com.aerospike"

scalaVersion := "2.10.6"

javacOptions ++= Seq("-source", "1.7", "-target", "1.7")

parallelExecution in test := false

libraryDependencies ++= Seq("org.apache.spark" %% "spark-core" % "1.6.1" % "provided",
	"org.apache.spark" %% "spark-sql" % "1.6.1" % "provided",
	"com.aerospike" % "aerospike-client" % "4.0.8",
	"com.aerospike" % "aerospike-helper-java" % "1.2.2",
  "joda-time" % "joda-time" % "2.9.9",
	"org.scalatest" %% "scalatest" % "2.2.1" % "test")

assemblyMergeStrategy in assembly := {
    case x if Assembly.isConfigFile(x) =>
      MergeStrategy.concat
    case PathList(ps @ _*) if Assembly.isReadme(ps.last) || Assembly.isLicenseFile(ps.last) =>
      MergeStrategy.rename
   case PathList("META-INF", "maven","com.aerospike","aerospike-client", "pom.properties") =>
      MergeStrategy.discard
    case PathList("META-INF", xs @ _*) =>
      (xs map {_.toLowerCase}) match {
        case ("manifest.mf" :: Nil) | ("index.list" :: Nil) | ("dependencies" :: Nil) =>
          MergeStrategy.discard
        case ps @ (x :: xs) if ps.last.endsWith(".sf") || ps.last.endsWith(".dsa") =>
          MergeStrategy.discard
        case "plexus" :: xs =>
          MergeStrategy.discard
        case "services" :: xs =>
          MergeStrategy.filterDistinctLines
        case ("spring.schemas" :: Nil) | ("spring.handlers" :: Nil) =>
          MergeStrategy.filterDistinctLines
        case _ => MergeStrategy.discard
      }
   case _ => MergeStrategy.first
}