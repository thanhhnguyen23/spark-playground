package com.sundogsoftware.spark

import org.apache.spark._
import org.apache.spark.SparkContext._
import org.apache.log4j._

/**
 * compute average number of friends by age in a social network
 */
object FriendsByAge {
  /**
   * function that splits a lien of input into (age, numFriends) tuples
   */
  def parseLine(line: String) = {
    
    // delimited by commas
    val fiends = line.sp
  }

  def main(args: Array[String]) {
    // set the logger level to only print errors
    Logger.getLogger("org").setLevel(Level.ERROR)
    
    // create a sparkcontext using eveyr core of the local machine
    val sc = new SparkContext("local[*]", "FriendsByAge")

    // load each line of the source data into an RDD
    val lines = sc.textFile("../fakefriends.csv")
    
    // use our parselines function to convert to (age, numFriends) tuples
    val rdd = lines.map(parseLine)

  }

}