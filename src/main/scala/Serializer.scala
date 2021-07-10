import java.io.{ByteArrayOutputStream, DataOutputStream}

import scala.collection.mutable.ArrayBuffer

object Serializer {
  def serializeDouble(i: Double) = {
    val bos = new ByteArrayOutputStream
    val dos = new DataOutputStream(bos)
    dos.writeDouble(i)
    dos.flush
    bos.toByteArray
  }

  def serializeInt(i: Int) = {
    val bos = new ByteArrayOutputStream
    val dos = new DataOutputStream(bos)
    dos.writeInt(i)
    dos.flush
    bos.toByteArray
  }

  def serializeIntToNormalizedBits(i: Int): Array[Boolean] = {
    val bos = new ByteArrayOutputStream
    val dos = new DataOutputStream(bos)
    dos.writeInt(i)
    dos.flush
    val ba = bos.toByteArray.map(byteToBooleans).reduce(_ ++ _).toArray
    var foundData = false
    (for(b <- ba) yield {
      if (b == true) {
        foundData = true
      }
      if(foundData) Some(b)
      else None
    })
      .filter(_.isDefined).map(_.get)
  }

  def serializeDoubleSeq(seq: Seq[Double]) = seq.map(v => serializeDouble(v)).reduce(_ ++ _)

  def byteToBooleans(b: Byte) =
    (0 to 7).foldLeft(ArrayBuffer[Boolean]())((bs, i) => bs += isBitSet(b, i))

  def isBitSet(byte: Byte, bit: Int) =
    ((byte >> bit) & 1) == 1

  def serializeIntSeq(seq: Seq[Int]) = seq.map(v => serializeInt(v)).reduce(_ ++ _)

  def serializeDoubleSeqToBits(seq: Seq[Double]) = serializeDoubleSeq(seq).flatMap(byteToBooleans)
  def serializeIntSeqToBits(seq: Seq[Int]) = serializeIntSeq(seq).flatMap(byteToBooleans)

  def serializeIntSeqToNormalizedBits(seq: Seq[Int]): Seq[Boolean] =
    seq.map(v => serializeIntToNormalizedBits(v)).reduce[Array[Boolean]](_ ++ _)
}
