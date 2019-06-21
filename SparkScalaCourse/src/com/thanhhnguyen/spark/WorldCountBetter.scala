package com.thanhhnguyen.spark

import org.apache.spark._
import org.apache.spark.SparkContext._
import org.apache.log4j._

/**
 * count up how many of each word occurs in a book using regular expressions
 */
object WorldCountBetter {
  def main(args: Array[String]){
    // set the log level
    Logger.getLogger("org").setLevel(Level.ERROR)
    
    val sc = new SparkContext("local[*]", "WordCount")
    
    // read each line of the book into an RDD
    val input = sc.textFile("../book.txt")
    
    
    // split using a regular expression that extracts words
    // flatMap is used because we want each word on a different line, instead of line
    val words = input.flatMap(x => x.split("\\W+"))

    // normalize everything to lowercase
    val lowercaseWords = words.map(x => x.toLowerCase())

    // count of the occurrences of each word
    val wordCounts = lowercaseWords.countByValue()
    
    // print the results
    wordCounts.foreach(println)

  }
}