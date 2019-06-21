package com.thanhhnguyen.spark

import org.apache.spark._
import org.apache.spark.SparkContext._
import org.apache.log4j._

/**
 * count up how many of each word occurs in book, using regular expressions, and sorted the final results
 */
object WordCountBetterSorted {

  def main(args: Array[String]){
    // set the log level
    Logger.getLogger("org").setLevel(Level.ERROR)
    
    val sc = new SparkContext("local[*]", "WordCount")
    
    // read each line of the book into an RDD
    val input = sc.textFile("../book.txt")
    
    
    // split using a regular expression that extracts words
    val words = input.flatMap(x => x.split("\\W+"))

    // normalize everything to lowercase
    val lowercaseWords = words.map(x => x.toLowerCase())
    
    // count of the occurrence of each word
    val wordCounts = lowercaseWords.map(x => (x, 1)).reduceByKey( (x,y) => x + y ) 
    
    // flip (word, count) tuples to (count, word) and then sort by key (the counts)
    val wordCountSorted = wordCounts.map( x => (x._2, x._1) ).sortByKey()
    
    // print the results, flipping the (count, word) results to word: count as we go
    for(result <- wordCountSorted){
      
      val count = result._1
      val word = result._2
      println(s"$word: $count")
    }
  }
}