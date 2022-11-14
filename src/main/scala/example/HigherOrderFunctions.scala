package example

object HigherOrderFunctions extends App {

  def sum(f: Int => Int, a: Int, b: Int): Int = {
    def loop(currentIteration: Int, accumulator: Int): Int =
      if (currentIteration > b) accumulator
      else loop(currentIteration + 1, f(currentIteration) + accumulator)

    loop(a, 0)
  }

  def sum2(f: Int => Int)(a: Int, b: Int): Int =
    if (a > b) 0 else f(a) + sum2(f)(a + 1, b)

  def product(f: Int => Int)(a: Int, b: Int): Int =
    if (a > b) 1
    else f(a) * product(f)(a + 1, b)

  def factorial(n: Int): Int = product(x => x)(1, n)

  def mapReduce(f: Int => Int, combine: (Int, Int) => Int, zero: Int)(a: Int, b: Int): Int =
    if (a > b) zero
    else combine(f(a), mapReduce(f, combine, zero)(a + 1, b))

  println("Suma de 1 a 5 = " + sum(x => x, 1, 5))
  println("Suma de 1 a 5 = " + sum2(x => x)(1, 5))

  println("Factorial de 5 = " + factorial(5))

  /**
   * product re definido en terminos de mapReduce:
   */
  def product3(f: Int => Int)(a: Int, b: Int): Int = mapReduce(f, (x, y) => x * y, 1)(a, b)


}
