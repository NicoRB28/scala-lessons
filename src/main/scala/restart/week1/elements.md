## Evaluation

---

### Substitution model:
The idea underlying this model is that all evaluation does is reduce an expression
to a value.
It can be applied to all expressions, as long as they have no side effects.

sumSquare(3, 2+2)
sumSquare(3, 4)
square(3)+square(4)
3*3 + 4*4

BUT: Does every expression reduce to a value (in a finite number of steps)?
No:

def loop: Int = loop

There are two strategies to replace the values:
call-by-value and call-by-name

Both strategies reduce to the same final values as long as:
1. the reduced expression consists of pure functions, and
2. both evaluations terminate.

Call-by-value has the advantage that it evaluates every function argument only once

Call-by-name has the advantage that a function argument is not evaluated if the
corresponding parameter is unused in the evaluation of the function body.

### Termination
a. If CBV evaluation of an expression _e_ terminates, then CBN evaluation of _e_
terminates, too.
b. The other direction is not true

i.e:

if we define: def firts(x: Int, y: Int) = x

and consider the expression first(1, loop)

under CBN: first(1, loop) -> 1

under CBV: first(1, loop) -> loop entra en infinite loop

In scala normally uses call by value mostly because for performance
BUT if the type of a function parameter starts with => it uses Call-by-Name

example:

def constOne(x: Int, y: => Int) = 1

constOne(1+2, loop)
constOne(3, loop)
1

and

constOne(loop, 1+2) => loop