package week3

import java.util.NoSuchElementException

trait List[T] {
  def isEmpty: Boolean

  def head: T

  def tail: List[T]
}

/*
 * because we add val head and val tail to the constructor
 * this also generates a head and tail field which in fact
 * are also legal implementations for the trait head and
 * tail methods.
 */
class Cons[T](val head: T, val tail: List[T]) extends List[T] {
  /*
   * It is always false because a Cons represents a non empty list
   */
  override def isEmpty: Boolean = false
}

class Nil[T] extends List[T] {
  override def isEmpty: Boolean = true

  override def head: Nothing = throw new NoSuchElementException("Nil.head")

  override def tail: Nothing = throw new NoSuchElementException("Nil.tail")
}


/**
 * Exercise:
 * Write a function "nth" that takes an integer "n" and a list and selects the n'th element of the list
 *
 * Elements are numbered from 0
 * If index is outside the range from 0 up to the length of the list minus one, IndexOutOfBoundException should be
 * thrown.
 *
 */
object Main extends App {
  def nth[T](n: Int, list: List[T]): T = {
    if (list.isEmpty) throw new IndexOutOfBoundsException
    else if (n == 0) list.head
    else nth(n - 1, list.tail)
  }

  val list1 = new Cons[Int](1, new Cons[Int](2, new Nil[Int]))

  println(nth(0, list1))
  println(nth(1, list1))
  println(nth(-1, list1)) // => should throw an IndexOutOfBoundException
  println(nth(3, list1)) // => should throw an IndexOutOfBoundException

}

