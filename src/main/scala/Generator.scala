import scala.math.Numeric

abstract class Generator[T](initialValue: T) {
  val x = initialValue
  def next(): Generator[T]
}


object Generator {
  def generateSeq[T](generator: Generator[T], count: Int): Seq[T] =
    if (count > 0) generator.x +: generateSeq(generator.next(), count - 1)
    else Seq()
}