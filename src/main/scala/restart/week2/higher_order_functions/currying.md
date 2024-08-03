## Curryng

___

If we look to:

def sumInts(a: Int, b: Int) = sum(x => x, a, b)
def sumCubes(a: Int, b: Int) = sum(x => x * x * x, a, b)
def sumFactorials(a: Int, b: Int) = sum(fact, a, b)

a and b parameters get passed unchanged from sumInts and sumCubes into sum.

Can we be even shorter by getting rid of these parameters?

if we rewrite sum as follows:

````scala
    def sum(f: Int => Int): (Int, Int) => Int = {
      def sumF(a: Int, b: Int): Int =
        if (a > b) 0
        else f(a) + sumF(a + 1, b)
      sumF // we return the function that accept 2 parameters
    } 
````
nex we can simplify the other declarations:

````scala
def sumInts = sum(x => x)

def sumCubes = sum(x => x * x * x)

def sumFactorials = sum(fact)

//these functions can in turn be applied like any other function:

sumCubes(1, 10) + sumFactorials(10, 20)
````

But we can also avoid the sumInts, sumCubes, ... middleman?
```scala
 sum(cube)(1, 10)
```

And as the definition of functions that return functions are so useful in
functional programming there is a special syntax for it in Scala:

```scala
 def sum(f: Int => Int) (a: Int, b: Int): Int =
  if(a > b) 0 else f(a) + sum(f)(a + 1, b)
```

