package com.thanhhnguyen.spark

import org.apache.spark._
import org.apache.spark.SparkContext._
import org.apache.log4j._

/**
 * find the movies with the most ratings
 */
object PopularMovies {
     

  def main(args: Array[String]) {
    Logger.getLogger("org").setLevel(Level.ERROR)
    
    val sc = new SparkContext("local[*]", "PopularMovies")   
    
    // Read in each row
    val lines = sc.textFile("../ml-100k/u.data")

    // map to (movieId, 1) tuples
    val movies = lines.map(x => (x.split("\t")(1).toInt,1))
    
    // count all movies 
    val movieCounts = movies.reduceByKey( (x, y) => x + y )
    
    // flip (movieId, count) to (count, movieId)
    val flipped = movieCounts.map( x => (x._2, x._1) )
    
    // sort
    val sortedMovies = flipped.sortByKey()
    
    // collect and print results
    val results = sortedMovies.collect()
    
    results.foreach(println)

  }
}