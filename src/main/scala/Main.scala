import java.io.DataOutputStream
import java.nio.ByteBuffer

import scala.util.Random

object Main extends App {
    val seq0 = Generator.generateSeq(LamerGenerator(301, 123, 43, 32), 100)
    val seq1 = Generator.generateSeq(BlumBlumShubaGenerator(341, 3), 100)

    println(s"Lamer: $seq0")
    val test11 = TestAlgorithms.test1Ints(seq0)
    println(s"\ttest1: ${test11}")
    val test12 = TestAlgorithms.test2Ints(seq0)
    println(s"\ttest2: ${test12}")

    println(s"BlumBlumShubaGenerator: $seq1")
    val test21 = TestAlgorithms.test1Ints(seq1)
    println(s"\ttest1: ${test21}")
    val test22 = TestAlgorithms.test2Ints(seq1)
    println(s"\ttest2: ${test22}")
}
