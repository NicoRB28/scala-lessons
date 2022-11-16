package week3

object RationalDos extends App {
  /**
   * Otra forma es encapsular dentro de la clase
   * la logica (funciones) encargadas de operar
   * sobre los datos almacenados en la clase
   * utilizando los metodos
   */
  class Rational(x: Int, y: Int) {
    require(y != 0, "denominator must not be zero")
    def this(x: Int) = this(x, 1) //second constructor syntax

    private def gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)
    val numerator   = x / gcd(x, y)
    val denominator = y / gcd(x, y)

    //def less(that: Rational) = numerator * that.denominator < that.numerator * denominator
    def < (that: Rational) = numerator * that.denominator < that.numerator * denominator

    def max(that: Rational) = if (this < that) that else this

    def + (that: Rational): Rational =
      new Rational(
        numerator * that.denominator + that.numerator * denominator,
        denominator * that.denominator
      )

    def neg():Rational = new Rational(-numerator, denominator)

    def - (that: Rational): Rational =
      this + that.neg()

    /**
     * Es mejor la opcion de simplificar en la construccion del racional
     * porque con valores grandes el toString no funcionara como se espera
     */
    //override def toString() = numerator / gcd(x, y) + "/" + denominator / gcd(x, y)
    override def toString() = numerator + "/" + denominator
  }

  val rationalOne = new Rational(1, 2)
  val rationalTwo = new Rational(2, 3)
  println("1/2 + 2/3 = " + (rationalOne + rationalTwo))

  val rational3 = new Rational(4, 7)
  val rational4 = new Rational(2, 5)
  println("4/7 - 2/5 = " + (rational3 - rational4))

  val rational5 = new Rational(5, 8).neg()
  val rational6 = new Rational(6, 7).neg()
  println("(-5/8) - (-6/7) = " + (rational5 - rational6))

  val y = new Rational(5, 7)
  println("5/7 + 5/7 = " + (y + y))

  val x = new Rational(1, 3)

  println("1/3 < 5/7 = " + (x - y))
  println("max(1/3, 5/7) = " + x.max(y))

  //val strange = new Rational(1, 0)
  val rationalSecondConstructor = new Rational(2)
  println("rationalSecondConstructor = "+ rationalSecondConstructor)
}
