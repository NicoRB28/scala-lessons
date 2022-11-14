package week3

object RationalDos extends App {
  /**
   * Otra forma es encapsular dentro de la clase
   * la logica (funciones) encargadas de operar
   * sobre los datos almacenados en la clase
   * utilizando los metodos
   */
  class Rational(x: Int, y: Int) {
    def numerator   = x
    def denominator = y

    def add(that: Rational): Rational =
      new Rational(
        numerator * that.denominator + that.numerator * denominator,
        denominator * that.denominator
      )

    def neg():Rational = new Rational(-numerator, denominator)

    def sub(that: Rational): Rational =
      add(that.neg())

    override def toString() = numerator + "/" + denominator
  }

  val rationalOne = new Rational(1, 2)
  val rationalTwo = new Rational(2, 3)
  println("1/2 + 2/3 = " + rationalOne.add(rationalTwo))

  val rational3 = new Rational(4, 7)
  val rational4 = new Rational(2, 5)
  println("4/7 - 2/5 = " + rational3.sub(rational4))

  val rational5 = new Rational(5, 8).neg()
  val rational6 = new Rational(6, 7).neg()
  println("(-5/8) - (-6/7) = " + rational5.sub(rational6))


}
