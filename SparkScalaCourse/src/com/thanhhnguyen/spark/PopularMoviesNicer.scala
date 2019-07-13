package com.thanhhnguyen.spark

import org.apache.spark.SparkContext._
import java.nio.charset.CodingErrorAction
import org.apache.spark._
import org.apache.log4j._
import scala.io.Source
import scala.io.Codec


/**
 * find the movies with the most ratings
 */
object PopularMoviesNicer {
  /**
   * load up a map of movie IDs to movie names
   */
  def loadMovieNames() : Map[Int, String] = {

    // handle character encoding issues:
    implicit val codec = Codec("UTF-8")
    codec.onMalformedInput(CodingErrorAction.REPLACE)
    codec.onUnmappableCharacter(CodingErrorAction.REPLACE)
    
    /**
     * create a map of Ints to Strings and populate it from u.item
     */
    var movieNames: Map[Int, String] = Map()
    
    val lines = Source.fromFile("../ml-100k/u.item").getLines()
    for(line <- lines){
      var fields = line.split('|')
      if(fields.length > 1){
        movieNames += (fields(0).toInt -> fields(1))
      }
    }
    return movieNames
  }

  /**
   * main function where the action happens
   */
  def main(args: Array[String]){
    
    // set the log level to only print errors
    Logger.getLogger("org").setLevel(Level.ERROR)
    
    // create a SparkContext using every core of the localhost machine
    val sc = new SparkContext("local[*]", "PopularMoviesNicer")
    
    // create a broadcast variable of our ID -> movie name map
    var nameDict = sc.broadcast(loadMovieNames)
    
    
    // read in each rating line
    val lines = sc.textFile("../ml-100k/u.data")
    
    // map to (movieID, 1) tuples
    val movies = lines.map(x => (x.split("\t")(1).toInt, 1))
    
    // count up all the 1's for each movie
    val movieCounts = movies.reduceByKey( (x,y) => x + y )
    
    // flip (movieID, count) to (count, movieID)
    val flipped = movieCounts.map( x => (x._2, x._1) )
    
    // sort
    val sortedMovies = flipped.sortByKey()
    
    // fold in the movies names from the broadcast variable
    val sortedMoviesWithNames = sortedMovies.map( x => (nameDict.value(x._2), x._1) )
    
    
    // collect and print results
    val results = sortedMoviesWithNames.collect()
    
    results.foreach(println)
    
  }
  
  
}