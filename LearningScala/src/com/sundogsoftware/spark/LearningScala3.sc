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
  }

  def cubeIt(x: Int): Int = {
    x * x * x
  }

  println(squareIt(5))
  println(cubeIt(2))



	// functions can take other functions as parameters aka first class citizens
	/*
		parameter type -> x: Int
		parameter type -> f: Int => Int where a function will transform an integer to another integer
		return type -> Int
	*/
	def transformInt(x: Int, f: Int => Int) : Int = {

		f(x)
	}
	// example 1
	val result = transformInt(2, cubeIt)
	println(result)
	
	// example 2
	val anotherResult = transformInt(2, squareIt)
	println(anotherResult)
	
	
	// "lambda functions", "anonymous functions", "function literals"

	transformInt(3, x => x * x * x)
	

}