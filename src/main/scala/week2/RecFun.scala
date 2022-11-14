package week2

object RecFun extends RecFunInterface {

  def main(args: Array[String]): Unit = {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(s"${pascal(col, row)} ")
      println()
    }
  }

  /**
   * Exercise 1
   */
  def pascal(c: Int, r: Int): Int = {
    def loop(x: Int, currentIter: Int, possibleResult: Int): Int = {
      if (currentIter > x)
        possibleResult
      else
        loop(x, currentIter + 1, possibleResult * currentIter)
    }

    def factorial(x: Int): Int = {
      loop(x, 1, 1)
    }

    factorial(r) / (factorial(c) * factorial(r - c))
  }

  /**
   * Exercise 2
   */
  def balance(chars: List[Char]): Boolean = {
    def loop(chars: List[Char], total: Int): Boolean = {
      if (chars.isEmpty) total == 0
      else if (chars.head == '(') loop(chars.tail, total + 1)
      else if (chars.head == ')') total > 0 && loop(chars.tail, total - 1)
      else loop(chars.tail, total)
    }

    loop(chars, 0)
  }

  /**
   * Exercise 3
   */
  def countChange(money: Int, coins: List[Int]): Int = {
    def loop(money: Int, coins: List[Int]): Int = {
      if (money < 0 || coins.isEmpty) 0
      else if (money == 0) 1
      else loop(money, coins.tail) + loop(money - coins.head, coins)
    }

    if (money <= 0 || coins.isEmpty) 0
    else loop(money, coins)
  }
}
