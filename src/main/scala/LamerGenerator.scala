



case class LamerGenerator(m: Int, a: Int, c: Int, override val x: Int) extends Generator[Int](x) {
  def next() = LamerGenerator(m, a, c, (a * x + c) % m)
}
