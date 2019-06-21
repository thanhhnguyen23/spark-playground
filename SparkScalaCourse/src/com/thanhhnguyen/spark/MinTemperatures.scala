package com.sundogsoftware.spark

import org.apache.spark._
import org.apache.spark.SparkContext._
import org.apache.log4j._
import scala.math.min

/**
 * find the minimum temperature by weather station
 */
object MinTemperatures {
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

    //TODO
    // create a sparkContext using every core of the local machine
    val sc = new SparkContext("local[*]", "MinTemperatures")

    // read each line of input data
    val lines = sc.textFile("../1800.csv")

    // convert to (stationID, entryType, temperature) tuples
    val parsedLines = lines.map(parseLine)

    // filter out all but TMIN entries
    val minTemps = parsedLines.filter(x => x._2 == "TMIN")

    // convert to (stationID, temperature)
    val stationTemps = minTemps.map(x => (x._1, x._3.toFloat))

    // reduce by stationID retaining the minimum temperature found
    val minTempsByStation = stationTemps.reduceByKey((x, y) => min(x, y))

    // collect, format, and print the results
    val results = minTempsByStation.collect()

    for (result <- results.sorted) {

      val station = result._1
      val temp = result._2
      val formattedTemp = f"$temp%.2f F"
      println(s"$station minimum temperature: $formattedTemp")

    }

  }
}