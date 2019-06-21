package com.thanhhnguyen.spark

import org.apache.spark._
import org.apache.spark.SparkContext._
import org.apache.log4j._

/**
 * compute average number of friends by age in a social network
 */
object FriendsByAge { 
  /**
   * function that splits a line of input into (age, numFriends) tuples
   */
  def parseLine(line: String) = {
    
    // delimited by commas
    val friends = line.split(",")
    
    // extract the age and numFriends fields, and convert to integers 
    val age = friends(2).toInt
    val numFriends = friends(3).toInt
    
    // create a tuple that is our result
    (age, numFriends)
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
    
    /*
     * age -> KEY
     * numFriends -> VALUE
     * 
     * 1. mapValues to convert each numFriends value to a tuple of (numFriends, 1)
     * 2. reduceByKey to sum up the total numFriends and total instance for each age, by adding together all the numFriends values and tally the ones
     */
    val totalsByAge = rdd.mapValues(x => (x, 1)).reduceByKey((x, y) => (x._1  + y._1, x._2 + y._2))
    
    /*
     * tuples (age, (totalFriends, totalInstances))
     * to compute the average we divide totalFriends / totalInstance for each age
     */
    val averagesByAge = totalsByAge.mapValues(x => x._1 / x._2)
    
    // collect the results from the RDD (collect (action) that will trigger the DAG and executes the job)
    val results = averagesByAge.collect()
    
    // sort and print the finals results
    results.sorted.foreach(println)
  }
}