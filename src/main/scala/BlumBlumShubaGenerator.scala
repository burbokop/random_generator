import spire.math.Integral
import spire.implicits._

case class BlumBlumShubaGenerator(m: Int, override val x: Int) extends Generator[Int](x) {
  def next() = BlumBlumShubaGenerator(m, (x * x) % m)
}
