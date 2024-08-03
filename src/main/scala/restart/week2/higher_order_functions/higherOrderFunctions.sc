
def product(f:Int => Int)(a: Int, b: Int): Int = {
  if (a > b) 1
  else f(a) * product(f)(a + 1, b)
}

product(x => x * x) (1, 4)
product(x => x * x) (1, 1)

def factorial(n: Int) = product(x => x)(1, n)

factorial(1)
factorial(2)
factorial(4)

def genericFunction(f:Int => Int,
                    combinationFunction: (Int, Int) => Int,
                    identity: Int)(a: Int, b: Int): Int = {
  if(a > b) identity
  else combinationFunction(
    a,
    genericFunction(f, combinationFunction, identity)(a + 1, b))
}

def factorial2(n: Int) = genericFunction(
  x => x,
  (x, y) => x * y,
  1
) (1, 5)

def product2(from: Int, to: Int) = genericFunction(
  x => x,
  (x, y) => x * y,
  1
) (from, to)

factorial2(5)
product2(1, 3)

