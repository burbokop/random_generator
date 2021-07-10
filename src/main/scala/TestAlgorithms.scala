import scala.annotation.tailrec
import scala.collection.mutable.ArrayBuffer

object TestAlgorithms {
  def test1Bits(seq: Seq[Boolean]) = {
    if(seq.length > 0) {
      val trueCount = seq.count(e => e)
      val k = trueCount.toDouble / seq.length.toDouble
      k >= 0.4827 && k <= 0.5173
    } else {
      false
    }
  }
  def test1Doubles(seq: Seq[Double]) = test1Bits(Serializer.serializeDoubleSeqToBits(seq))
  def test1Ints(seq: Seq[Int]) = test1Bits(Serializer.serializeIntSeqToBits(seq))

  def test2Bits(seq: Seq[Boolean]) = {
    if (seq.length > 0) {
      val groups = seq.toArray.grouped(4).toArray
      @tailrec
      def count(array: Array[Array[Boolean]], map: Map[Array[Boolean], Int] = Map()): Map[Array[Boolean], Int] = {
        if (array.length > 0) count(array.tail, map + (array.head -> (map.get(array.head).getOrElse(0) + 1)))
        else map
      }
      val c = count(groups).toList.map(_._2)
      val testValue = c.length / groups.length * c.map(v => v * v).sum - groups.length
      println(s"test2Bits: $testValue")
      1.03 < testValue && testValue < 157.4
    } else {
      false
    }
  }
  def test2Doubles(seq: Seq[Double]) = test2Bits(Serializer.serializeDoubleSeqToBits(seq))
  def test2Ints(seq: Seq[Int]) = test2Bits(Serializer.serializeIntSeqToBits(seq))

}
