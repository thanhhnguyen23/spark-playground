package com.sundogsoftware.spark

import org.apache.spark._
import org.apache.spark.SparkContext._
import org.apache.log4j._
/** 
 *  add up amount spent by customer
 *  
 *  1. split each comma-delimited line into fields
 *  2. map each line to key/value pairs of customers ID and dollar amount (customerID, dollarAmount)
 *  3. use reduceByKey to add up amount spent by CustomerID
 *  4. collect() the results and print them
 */
object PurchaseByCustomerSorted {

  def parseLine(line: String) = {
    val orders = line.split(",")
    val customerId = orders(0).toInt
    val amt = orders(2).toFloat

    (customerId, amt)
  }
  
  def main(args: Array[String]){
    Logger.getLogger("org").setLevel(Level.ERROR)
    
    val sc = new SparkContext("local[*]", "PurchaseByCustomerSorted")
    
    val input = sc.textFile("../customer-orders.csv")
    
    val rdd = input.map(parseLine)
    
    /**
     * 	must use reduceByKey to aggregate totals by customerID
     */

    val totalsByCustomerId = rdd.reduceByKey( (x, y) => x + y )

    // sorting amount column
    val totalsByCustomerIdSorted = totalsByCustomerId.map( x => (x._2, x._1) ).sortByKey()

    val result = totalsByCustomerIdSorted.collect()
    
    result.foreach(println)
  }
}