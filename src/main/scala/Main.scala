import java.io.DataOutputStream
import java.nio.ByteBuffer

import scala.util.Random

object Main extends App {
    val seqLamer0 = Generator.generateSeq(LamerGenerator(301, 123, 405, 32), 20)
    val seqLamer1 = Generator.generateSeq(LamerGenerator(301, 123, 305, 32), 20)
    val seq1 = Generator.generateSeq(BlumBlumShubaGenerator(341, 3), 20)

    println(s"Lamer0: $testLamer0")
    println(s"Lamer1: $testLamer1")
    val testLamer0 = TestAlgorithms.test1Bits(Serializer.serializeIntSeqToNormalizedBits(seqLamer0))
    val testLamer1 = TestAlgorithms.test1Bits(Serializer.serializeIntSeqToNormalizedBits(seqLamer1))
    println(s"\ttest1: seqLamer0: ${testLamer0}")
    println(s"\ttest1: seqLamer1: ${testLamer1}")

    val test12 = TestAlgorithms.test2Bits(Serializer.serializeIntSeqToNormalizedBits(testLamer0))
    println(s"\ttest2: testLamer0: ${testLamer0}")

    println(s"BlumBlumShubaGenerator: $seq1")
    val test21 = TestAlgorithms.test1Bits(Serializer.serializeIntSeqToNormalizedBits(seq1))
    println(s"\ttest1: ${test21}")
    val test22 = TestAlgorithms.test2Bits(Serializer.serializeIntSeqToNormalizedBits(seq1))
    println(s"\ttest2: ${test22}")
}
