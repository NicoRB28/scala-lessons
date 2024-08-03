import scala.math.abs

def sqrt(x: Double)  = {
  def isGoodEnough(guess: Double) =
    abs(guess * guess - x) / x < 0.001

  def improve(guess: Double) =
    (guess + x / guess) / 2

  def sqrtIter(guess: Double): Double =
    if(isGoodEnough(guess)) guess
    else sqrtIter(improve(guess))

  sqrtIter(1.0)
}


def tailFactorial(x: Int): Int = {
  //this is a loop like replacement
  def f(currentIter: Int, possibleResult: Int): Int = {
    if (currentIter > x)
      possibleResult
    else
      f(currentIter + 1, possibleResult * currentIter)
  }
  f(1, 1)
}
//analisis de tailFactorial
// 1 > 2 no, entonces f(currentIter = 1+1, posibleResult = 1*1)
  //2>2 no, entonces  f(currentIter  = 2+1, posibleResult  = 1*2)
    //3>2 si, entonces 2 fin

def pascal(c: Int, r: Int): Int = {
  tailFactorial(r) / (tailFactorial(c) * tailFactorial(c))
}

sqrt(2)
sqrt(4)
sqrt(1e60)

tailFactorial(5)
tailFactorial(2)
tailFactorial(1)
tailFactorial(3)
tailFactorial(0)
tailFactorial(4)

pascal(2, 4)
pascal(2, 5)

