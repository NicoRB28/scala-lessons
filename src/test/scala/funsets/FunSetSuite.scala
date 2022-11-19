package funsets

/**
 * This class is a test suite for the methods in object FunSets.
 *
 * To run this test suite, start "sbt" then run the "test" command.
 */
class FunSetSuite extends munit.FunSuite {

  import FunSets._

  test("contains is implemented") {
    assert(contains(x => true, 100))
  }

  /**
   * When writing tests, one would often like to re-use certain values for multiple
   * tests. For instance, we would like to create an Int-set and have multiple test
   * about it.
   *
   * Instead of copy-pasting the code for creating the set into every test, we can
   * store it in the test class using a val:
   *
   *   val s1 = singletonSet(1)
   *
   * However, what happens if the method "singletonSet" has a bug and crashes? Then
   * the test methods are not even executed, because creating an instance of the
   * test class fails!
   *
   * Therefore, we put the shared values into a separate trait (traits are like
   * abstract classes), and create an instance inside each test method.
   *
   */

  trait TestSets {
    val s1 = singletonSet(1)
    val s2 = singletonSet(2)
    val s3 = singletonSet(3)
  }

  /**
   * This test is currently disabled (by using @Ignore) because the method
   * "singletonSet" is not yet implemented and the test would fail.
   *
   * Once you finish your implementation of "singletonSet", remove the
   * .ignore annotation.
   */
  test("singleton set one contains one") {

    /**
     * We create a new instance of the "TestSets" trait, this gives us access
     * to the values "s1" to "s3".
     */
    new TestSets {
      /**
       * The string argument of "assert" is a message that is printed in case
       * the test fails. This helps identifying which assertion failed.
       */
      assert(contains(s1, 1), "Singleton")
    }
  }

  test("union contains all elements of each set") {
    new TestSets {
      val s: FunSet = union(s1, s2)
      assert(contains(s, 1), "Union 1")
      assert(contains(s, 2), "Union 2")
      assert(!contains(s, 3), "Union 3")
    }
  }

  test("intersection") {
    new TestSets {
      val a: FunSet = singletonSet(1)
      val b: FunSet = singletonSet(3)
      val unionAB: FunSet = union(a, b)
      val s: FunSet = union(s1, s2)
      val intersection: FunSet = intersect(unionAB, s)
      assert(contains(intersection, 1), "Intersection 1")
      assert(!contains(intersection, 2), "Intersection 2")
      assert(!contains(intersection, 3), "Intersection 3")
    }
  }

  test("diff") {
    new TestSets {
      val a: FunSet = singletonSet(1)
      val b: FunSet = singletonSet(3)
      val unionAB: FunSet = union(a, b)
      val diffABS3: FunSet = diff(unionAB, s3)

      assert(contains(diffABS3, 1), "Intersection 1")
      assert(!contains(diffABS3, 2), "Intersection 2")
      assert(!contains(diffABS3, 3), "Intersection 3")
    }
  }

  test("filter") {
    new TestSets {
      val a: FunSet = singletonSet(1)
      val b: FunSet = singletonSet(3)
      val unionAB: FunSet = union(a, b)
      def predicate: Int => Boolean = (x: Int) => if (x > 1) true else false
      val filtered: FunSet = filter(unionAB, predicate)
      assert(!contains(filtered, 1), "shouldn't have 1")
      assert(!contains(filtered, 2), "shouldn't have 2")
      assert(contains(filtered, 3), "should have 3")
    }
  }

  test("forAll") {
    new TestSets {
      val a: FunSet = singletonSet(1)
      val b: FunSet = singletonSet(3)
      val unionAB: FunSet = union(a, b)
      def predicate: Int => Boolean = (x: Int) => if (x > 1) true else false
      val areAllGreaterThatOne : Boolean = forall(unionAB, predicate)
      assertEquals(areAllGreaterThatOne, false)
    }
  }

  test("exist") {
    new TestSets {
      val a: FunSet = singletonSet(1)
      val b: FunSet = singletonSet(3)
      val unionAB: FunSet = union(a, b)
      def predicate: Int => Boolean = (x: Int) => if (x > 5) true else false
      val isOneGreaterThanFive : Boolean = exists(unionAB, predicate)
      assertEquals(isOneGreaterThanFive, false)
    }

    new TestSets {
      val a: FunSet = singletonSet(1)
      val b: FunSet = singletonSet(3)
      val unionAB: FunSet = union(a, b)
      def predicate: Int => Boolean = (x: Int) => if (x > 1 && x < 4) true else false
      val isOneGreaterThanOneAndLessThanFour : Boolean = exists(unionAB, predicate)
      assertEquals(isOneGreaterThanOneAndLessThanFour, true)
    }
  }

  test("map") {
    new TestSets {
      val a: FunSet = singletonSet(1)
      val b: FunSet = singletonSet(3)
      val unionAB: FunSet = union(a, b)
      val duplicatedValues: FunSet = map(unionAB, x => x * 2)

      assertEquals(contains(duplicatedValues, 2), true)
      assertEquals(contains(duplicatedValues, 6), true)
      assertEquals(contains(duplicatedValues, 1), false)
      assertEquals(contains(duplicatedValues, 3), false)
    }
  }

  import scala.concurrent.duration._
  override val munitTimeout = 10.seconds
}
