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
	def transformInt(x: Int, f: Int => Int) : Int = {

		f(x)
	}                                         //> transformInt: (x: Int, f: Int => Int)Int
	// example 1
	val result = transformInt(2, cubeIt)      //> result  : Int = 8
	println(result)                           //> 8
	
	// example 2
	val anotherResult = transformInt(2, squareIt)
                                                  //> anotherResult  : Int = 4
	println(anotherResult)                    //> 4
	

	
}