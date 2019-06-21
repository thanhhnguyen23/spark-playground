package com.sundogsoftware.spark

import org.apache.spark._
import org.apache.spark.SparkContext._
import org.apache.log4j._
import scala.math.max

/**
 * find the maximum temperature by weather station
 */
object MaxTemperatures {

  def parseLine(line: String) = {
    val fields = line.split(",")
    val stationID = fields(0)
    val entryType = fields(2)
    val temperature = fields(3).toFloat * 0.1f * (9.0f / 5.0f) + 32.0f
    (stationID, entryType, temperature)
  }

  def main(args: Array[String]) {
    // set the log level to only print errors
    Logger.getLogger("org").setLevel(Level.ERROR)

    // create a sparkContext using every core of the local machine
    val sc = new SparkContext("local[*]", "MaxTemperatures")

    // read each line of input data
    val lines = sc.textFile("../1800.csv")

    // convert to (stationID, entryType, temperature) tuples
    val parsedLines = lines.map(parseLine)

    // filter out all but TMIN entries
    val maxTemps = parsedLines.filter(x => x._2 == "TMAX")

    // convert to (stationID, temperature)
    val stationTemps = maxTemps.map(x => (x._1, x._3.toFloat))

    // reduce by stationID retaining the maximum temperature found
    val maxTempsByStation = stationTemps.reduceByKey((x, y) => max(x, y))

    // collect, format, and print the results
    val results = maxTempsByStation.collect()

    for (result <- results.sorted) {

      val station = result._1
      val temp = result._2
      val formattedTemp = f"$temp%.2f F"
      println(s"$station maximum temperature: $formattedTemp")
    }
  }
}