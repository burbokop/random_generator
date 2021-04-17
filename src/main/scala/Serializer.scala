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

  def serializeDoubleSeq(seq: Seq[Double]) = seq.map(v => serializeDouble(v)).reduce(_ ++ _)

  def byteToBooleans(b: Byte) =
    (0 to 7).foldLeft(ArrayBuffer[Boolean]())((bs, i) => bs += isBitSet(b, i))

  def isBitSet(byte: Byte, bit: Int) =
    ((byte >> bit) & 1) == 1

  def serializeIntSeq(seq: Seq[Int]) = seq.map(v => serializeInt(v)).reduce(_ ++ _)

  def serializeDoubleSeqToBits(seq: Seq[Double]) = serializeDoubleSeq(seq).flatMap(byteToBooleans)
  def serializeIntSeqToBits(seq: Seq[Int]) = serializeIntSeq(seq).flatMap(byteToBooleans)
}
