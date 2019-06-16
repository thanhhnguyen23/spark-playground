package com.sundogsoftware.spark

// functions
object LearningScala3 {
  /*
		parameter -> x : Int
		return type -> Int
	*/
  def squareIt(x: Int): Int = {
    // no return is needed because expressions are automatically evaluated
    x * x
  }                                               //> squareIt: (x: Int)Int

  def cubeIt(x: Int): Int = {
    x * x * x
  }                                               //> cubeIt: (x: Int)Int

  println(squareIt(5))                            //> 25
  println(cubeIt(2))                              //> 8

  // functions can take other functions as parameters aka first class citizens
  /*
		parameter type -> x: Int
		parameter type -> f: Int => Int where a function will transform an integer to another integer
		return type -> Int
	*/
  def transformInt(x: Int, f: Int => Int): Int = {
    f(x)
  }                                               //> transformInt: (x: Int, f: Int => Int)Int
  // example 1
  val result = transformInt(2, cubeIt)            //> result  : Int = 8
  println(result)                                 //> 8

  // example 2
  val anotherResult = transformInt(2, squareIt)   //> anotherResult  : Int = 4
  println(anotherResult)                          //> 4

  // "lambda functions", "anonymous functions", "function literals"

  // example 3
  transformInt(3, x => x * x * x) // function literal
                                                  //> res0: Int = 27

  // example 4
  transformInt(10, x => x / 2)                    //> res1: Int = 5

  // example 5
  transformInt(2, x => { val y = x * 2; y * y })  //> res2: Int = 16

  /*
			exercise
			
			built-in .toUpperCase() method
			
			1. write a function that converts a string to uppercase, and use that function of a few test strings
			2. refactor code from #1 using a function literal instead of a separate function
	*/

	// solution 1
	def convertStringToUppercase(){
		val myString = "thanh nguyen"
		val myOtherString = "friends"
		val yetAnotherString = "The Movie Title that nEvEr gETS their letter casing right"

		println(myString.toUpperCase())
		println(myOtherString.toUpperCase())
		println(yetAnotherString.toUpperCase())
	}                                         //> convertStringToUppercase: ()Unit

	convertStringToUppercase()                //> THANH NGUYEN
                                                  //| FRIENDS
                                                  //| THE MOVIE TITLE THAT NEVER GETS THEIR LETTER CASING RIGHT

	// solution 2
  def convert(x: String, f: String => String) : String = {
  	f(x)
  }                                               //> convert: (x: String, f: String => String)String

	convert("hello", x => x.toUpperCase)      //> res3: String = HELLO
	

	
	
	
	
	
}