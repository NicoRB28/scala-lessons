package week4



trait List[T] {
  def isEmpty: Boolean
  def head: T
  def tail: List[T]
}

class Cons[T](val head: T, val tail: List[T]) extends List[T] {
  override def isEmpty: Boolean = false
}

class Nil[T] extends List[T] {
  override def isEmpty: Boolean = true
  override def head: T = throw new NoSuchElementException("Nil.head")
  override def tail: List[T] = throw new NoSuchElementException("Nil.tail")
}

object List {
  //List(1, 2) = List.apply(1,2)
  def apply[T](first: T, second: T): List[T] = new Cons(first, new Cons(second, new Nil))
  def apply[T](): List[T] = new Nil
}