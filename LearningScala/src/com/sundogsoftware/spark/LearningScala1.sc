package com.sundogsoftware.spark

// variables, println, string interpolation
object LearningScala1 {
  val hello: String = "Xin Chao!"                 //> hello  : String = Xin Chao!
  println(hello)                                  //> Xin Chao!
  
  val immutableHelloThere = hello + " handsome person!"
                                                  //> immutableHelloThere  : String = Xin Chao! handsome person!
  println(immutableHelloThere)                    //> Xin Chao! handsome person!
  
  val numberOne : Int = 1                         //> numberOne  : Int = 1
  val truth : Boolean = true                      //> truth  : Boolean = true
  val letterA : Char = 'a'                        //> letterA  : Char = a
  val pi : Double = 3.14159265                    //> pi  : Double = 3.14159265
  val piSinglePrecision : Float = 3.14159265f     //> piSinglePrecision  : Float = 3.1415927
	val smallNumber : Byte = 127              //> smallNumber  : Byte = 127
	
	println(f"Pi is about $piSinglePrecision%.3f")
                                                  //> Pi is about 3.142
	println(f"Zero padding on the left: $numberOne%05d")
                                                  //> Zero padding on the left: 00001
	println(f"this is a string with variables inside $numberOne $truth $letterA")
                                                  //> this is a string with variables inside 1 true a
	
	println(s"this is prefix with expressions inside like ${1 + 2 + 3 - 2}${2}")
                                                  //> this is prefix with expressions inside like 42
                                                  

	val theUltimateAnswer : String = "To life, the universe, and everything 42"
                                                  //> theUltimateAnswer  : String = To life, the universe, and everything 42
	val pattern = """.* ([\d]+).*""".r        //> pattern  : scala.util.matching.Regex = .* ([\d]+).*
	val pattern(answerString) = theUltimateAnswer
                                                  //> answerString  : String = 42
	val answer = answerString.toInt           //> answer  : Int = 42
	println(answer)                           //> 42
	
	/*
		exercise

		1. takes value of pi
		2. doubles pi
		3. print 3 decimal places of precision within string
	*/

	val doublePi = pi * 2                     //> doublePi  : Double = 6.2831853
	println(doublePi)                         //> 6.2831853
	
	// print within a string
	println(f"this is a string with pi with three decimals places of precision: $doublePi%.3f")
                                                  //> this is a string with pi with three decimals places of precision: 6.283
}