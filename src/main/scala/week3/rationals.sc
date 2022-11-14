class Rational(x: Int, y: Int) {
  def numerator   = x
  def denominator = y
}

/**
 * separando las funciones de la clase es una de las formas de hacerlo
 * y se fundamenta en utilizar la clase solo como estructura contenedora
 * de datos.
 */
def addRational(rational1: Rational, rational2: Rational): Rational =
  new Rational(
    rational1.numerator * rational2.denominator + rational2.numerator * rational1.denominator,
    rational1.denominator * rational2.denominator
  )

def makeString(rational: Rational): String =
  rational.numerator + "/" + rational.denominator

val rational = new Rational(1, 2)
rational.numerator
rational.denominator

makeString(addRational(new Rational(1, 2), new Rational(2, 3)))


