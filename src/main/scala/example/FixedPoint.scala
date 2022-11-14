package example

import scala.math.abs

object FixedPoint extends App {
  val tolerance = 0.0001
  def isCloseEnough(x: Double, y: Double) =
    abs((x - y) / x) / x < tolerance
  def fixedPoint(f: Double => Double) (firstGuess: Double): Double = {
    def iterate(guess: Double): Double = {
      val next = f(guess)
      if (isCloseEnough(guess, next)) next
      else iterate(next)
    }
    iterate(firstGuess)
  }

  println(fixedPoint(x => 1 + x/2) (1))

  def averageDamp(f: Double => Double)(x: Double) = (x + f(x)) / 2

  def sqr(x: Double): Double =
    fixedPoint(averageDamp(y => x / y))(1)

  println("sqr(2) = "   +   sqr(2))
  println("sqr(9) = "   +   sqr(9))
  println("sqr(16) = "  +  sqr(16))

}
