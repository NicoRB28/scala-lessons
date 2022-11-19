abstract class IntSet {
  def incl(x: Int): IntSet
  def contains(x: Int): Boolean
}

class NonEmpty(value: Int, left: IntSet, right: IntSet) extends IntSet {

  override def incl(x: Int):IntSet = {
    if (x < value)      new NonEmpty(value, left incl x, right)
    else if (x > value) new NonEmpty(value, left, right incl x)
    else this
  }

  override def contains(x: Int) = {
    if (x < value) left  contains x
    if (x > value) right contains x
    else true
  }

  override def toString: String = "{" + left + value + right + "}"
}

/*
 Object is a singleton
 */
object Empty extends IntSet {

  override def incl(x: Int) = new NonEmpty(x, Empty, Empty)

  override def contains(x: Int) = false

  override def toString: String = "."
}

val t1 = new NonEmpty(3, Empty, Empty)
val t2 = t1 incl 4
t2 contains 4
t2 contains 3
t2 contains 100
t1 contains 100
