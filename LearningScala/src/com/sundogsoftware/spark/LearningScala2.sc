package com.sundogsoftware.spark

// control flow
object LearningScala2 {
	// if else
	if (1 > 3) println("impossible") else println("the world make sense")
                                                  //> the world make sense
	

	// matching works like switch statement
	val number = 3                            //> number  : Int = 3
	number match{
		case 1 => println("One")
		case 2 => println("Two")
		case 3 => println("Three")
		case _ => println("something else")
	}                                         //> Three
	
	// for loops
	for(x <- 1 to 4){
		val squared = x * x
		println(squared)
	}                                         //> 1
                                                  //| 4
                                                  //| 9
                                                  //| 16
	
	// while loops
	var x = 10                                //> x  : Int = 10
	while (x >= 0){
		println(x)
		x -= 1
	}                                         //> 10
                                                  //| 9
                                                  //| 8
                                                  //| 7
                                                  //| 6
                                                  //| 5
                                                  //| 4
                                                  //| 3
                                                  //| 2
                                                  //| 1
                                                  //| 0
                                                  

                                                  
	var y = 0                                 //> y  : Int = 0
	while ( y <= 10 ) {
		println(y)
		y += 1
	}                                         //> 0
                                                  //| 1
                                                  //| 2
                                                  //| 3
                                                  //| 4
                                                  //| 5
                                                  //| 6
                                                  //| 7
                                                  //| 8
                                                  //| 9
                                                  //| 10
                                                  
                                                  
	{val x = 10; x + 21}                      //> res0: Int = 31
	println({val x = 10; x + 21})             //> 31
	
	/*
		exercise
		print first 10 values of the fibonacci sequence
		
		0, 1, 1, 2, 3, 5, 8, 13, 21, 34
	*/
// 	fib example 1
//	def fib() : Int = {
//		var a = 0
//		var b = 1
//		var i = 0
//
//		while(i < 4){
//		val c = a + b
//		a = b
//		b = c
//		i += 1
//		}
//		return a
//	}
// 	fib()

// fib exmaple 2
	def fib(n : Int) : Int = n match{
		case 0 | 1 => n
		case _ => fib(n-1) + fib(n -2)
	}                                         //> fib: (n: Int)Int

	fib(10)                                   //> res1: Int = 55
}