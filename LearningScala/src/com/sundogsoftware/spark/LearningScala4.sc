package com.sundogsoftware.spark

import scala.util.Try

// scala data structures

object LearningScala4 {
  // tuples are often used because they are immutable
  val batCave = ("Batmobile", "super fast computer that has access to everything", "other cool bat themed gadgets")
                                                  //> batCave  : (String, String, String) = (Batmobile,super fast computer that ha
                                                  //| s access to everything,other cool bat themed gadgets)

  println(batCave)                                //> (Batmobile,super fast computer that has access to everything,other cool bat 
                                                  //| themed gadgets)

  // ONE-BASED index as opposed to ZERO-BASED index

  println(batCave._1)                             //> Batmobile
  println(batCave._2)                             //> super fast computer that has access to everything
  println(batCave._3)                             //> other cool bat themed gadgets

  // key/value pair with ->
  val batMobile = "bombs" -> "with gps tracking devices attached"
                                                  //> batMobile  : (String, String) = (bombs,with gps tracking devices attached)
  println(batMobile._2)                           //> with gps tracking devices attached
  println(batMobile)                              //> (bombs,with gps tracking devices attached)

  // mix different types in a tuple
  val aBunchOfStuff = ("Bruce Wayne", 1976, true) //> aBunchOfStuff  : (String, Int, Boolean) = (Bruce Wayne,1976,true)

  /*
		lists are ZERO-BASED index
		collection object that has more functionality than tuples
		singly linked list under the hood
	*/
  val shipList = List("Enterprise", "Defiant", "Voyager", "Deep Space Nine")
                                                  //> shipList  : List[String] = List(Enterprise, Defiant, Voyager, Deep Space Nin
                                                  //| e)
  // val shipList = List("Ferrari", "Lamborghini", "McLaren", "M3", "Fair Lady")

  // access individual members () with ZERO-BASED index
  println(shipList(1))                            //> Defiant

  // retrieves the first item
  println(shipList.head)                          //> Enterprise
  // retrieves all items except the first item
  println(shipList.tail)                          //> List(Defiant, Voyager, Deep Space Nine)

  // iterating through a list
  // for (ship <- shipList {println(ship)}

  for (ship <- shipList) println(ship)            //> Enterprise
                                                  //| Defiant
                                                  //| Voyager
                                                  //| Deep Space Nine

  // applying function literal to a list
  // map() can be used  to apply any function to every item in a collection

  var backwardShips = shipList.map((ship: String) => ship.reverse)
                                                  //> backwardShips  : List[String] = List(esirpretnE, tnaifeD, regayoV, eniN eca
                                                  //| pS peeD)

  var upperShips = shipList.map((ship: String) => ship.toUpperCase)
                                                  //> upperShips  : List[String] = List(ENTERPRISE, DEFIANT, VOYAGER, DEEP SPACE 
                                                  //| NINE)

  // reduce can be used to combine together all the items in a collection using some function
  val numberList = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15)
                                                  //> numberList  : List[Int] = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 1
                                                  //| 4, 15)
  val sum = numberList.reduce((x: Int, y: Int) => x + y)
                                                  //> sum  : Int = 120

  println(sum)                                    //> 120

  // filter() can remove stuff you don't want
  val iHateFives = numberList.filter((x: Int) => x != 5)
                                                  //> iHateFives  : List[Int] = List(1, 2, 3, 4, 6, 7, 8, 9, 10, 11, 12, 13, 14, 
                                                  //| 15)
  val iHateThrees = numberList.filter(_ != 3)     //> iHateThrees  : List[Int] = List(1, 2, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14,
                                                  //|  15)

  // concat list
  var moreNumbers = List(111, 222, 333)           //> moreNumbers  : List[Int] = List(111, 222, 333)
  val lostOfNumbes = numberList ++ moreNumbers    //> lostOfNumbes  : List[Int] = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13,
                                                  //|  14, 15, 111, 222, 333)

  val reversed = numberList.reverse               //> reversed  : List[Int] = List(15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2
                                                  //| , 1)
  val sorted = reversed.sorted                    //> sorted  : List[Int] = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 1
                                                  //| 5)
  val lotsOfDuplicates = numberList ++ numberList //> lotsOfDuplicates  : List[Int] = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12,
                                                  //|  13, 14, 15, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15)
  val distinctValues = lotsOfDuplicates.distinct  //> distinctValues  : List[Int] = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 1
                                                  //| 3, 14, 15)
  val maxValue = numberList.max                   //> maxValue  : Int = 15
  val total = numberList.sum                      //> total  : Int = 120
  val hasThree = iHateThrees.contains(3)          //> hasThree  : Boolean = false

  // maps

  val shipMap = Map("Kirk" -> "Enterprise", "Picard" -> "Enterprise-D", "Sisko" -> "Deep Space Nine", "Janeway" -> "Voyager")
                                                  //> shipMap  : scala.collection.immutable.Map[String,String] = Map(Kirk -> Ente
                                                  //| rprise, Picard -> Enterprise-D, Sisko -> Deep Space Nine, Janeway -> Voyage
                                                  //| r)

	val results = shipMap.get("Janeway")      //> results  : Option[String] = Some(Voyager)
	println(results)                          //> Some(Voyager)


	// dealing with missing keys
	shipMap.contains("Thanh")                 //> res0: Boolean = false
	
	val archersShip = util.Try(shipMap("Thanh's awesome ship!")) getOrElse "Can't find ship"
                                                  //> archersShip  : String = Can't find ship
                                                  
	println(archersShip)                      //> Can't find ship
	
	/*
		exercise
		1. create a list of numbers 1 20
		2. print out numbers that are evenly divisible by there by using the modula operator
		3. refactor code to use filter function and use the rules #2
	*/
	// example 1
	val x = List.range(1,21)                  //> x  : List[Int] = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16
                                                  //| , 17, 18, 19, 20)
	val removeThrees = x.filter(_ % 3 != 0)   //> removeThrees  : List[Int] = List(1, 2, 4, 5, 7, 8, 10, 11, 13, 14, 16, 17, 
                                                  //| 19, 20)
	println(x)                                //> List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20)
                                                  //| 

	// example 2
	println(x)                                //> List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20)
                                                  //| 
  val removeMultiplesOfThree = x.filter((x: Int) => x % 3 != 0 )
                                                  //> removeMultiplesOfThree  : List[Int] = List(1, 2, 4, 5, 7, 8, 10, 11, 13, 14
                                                  //| , 16, 17, 19, 20)
	println(removeMultiplesOfThree)           //> List(1, 2, 4, 5, 7, 8, 10, 11, 13, 14, 16, 17, 19, 20)
	
	
	
	
	
}