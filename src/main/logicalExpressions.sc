def and(x: Boolean, y: Boolean) =
  if (x) y else false


def or(x: Boolean, y: => Boolean) =
  if (x) true else y

println(or(true, true))
println(or(true, false))
println(or(false, false))
println(or(false, true))

