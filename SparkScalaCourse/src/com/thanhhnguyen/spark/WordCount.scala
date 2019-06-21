package com.thanhhnguyen.spark

import org.apache.spark._
import org.apache.spark.SparkContext._
import org.apache.log4j._


/**
 * count up how many of each word appears in a book
 */
object WordCount {
  def main(args: Array[String]){
    
    // set the log level
    Logger.getLogger("org").setLevel(Level.ERROR)
    
    val sc = new SparkContext("local[*]", "WordCount")
    
    // read each line of book into an RDD
    val input = sc.textFile("../book.txt")
    
    // split into words separated by a space character
    val words = input.flatMap(x => x.split(" "))
    
    // count up occurrences of each word
    val wordCounts = words.countByValue()
    
    // print the results
    wordCounts.foreach(println)
  }
}