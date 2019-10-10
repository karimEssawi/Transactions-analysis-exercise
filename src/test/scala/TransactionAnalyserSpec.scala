import org.scalatest.junit.JUnitRunner
import org.scalatest.{FunSpec, Matchers}
import quantexa.{Transaction, TransactionAnalyser}

//@RunWith(classOf[JUnitRunner])
class TransactionAnalyserSpec extends FunSpec with Matchers {

  val transactions = Seq(
    Transaction("T0001", "A27", 1, "GG", 338.11),
    Transaction("T0002", "A5", 1, "BB", 677.89),
    Transaction("T00070", "A40", 2, "DD", 633.84),
    Transaction("T00071", "A17", 2, "BB", 533.15),
    Transaction("T000109", "A45", 3, "BB", 695.21),
    Transaction("T000110", "A44", 3, "AA", 501.71),
    Transaction("T000135", "A29", 4, "FF", 827.53),
    Transaction("T000136", "A18", 4, "EE", 718.32),
    Transaction("T000200", "A42", 5, "FF", 174.04),
    Transaction("T000201", "A47", 5, "FF", 900.67),
    Transaction("T000211", "A32", 6, "EE", 877.00),
    Transaction("T000212", "A19", 6, "GG", 612.83)
  )

  val transactionAnalyser = new TransactionAnalyser(transactions)

  describe("Transaction Analyser") {
    it("Should calculate total transactions per day") {
      transactionAnalyser.calcTotalPerDay() shouldBe
        Map(1 -> 1016.0, 2 -> 1166.99, 3 -> 1196.92, 4 -> 1545.85, 5 -> 1074.71, 6 -> 1489.83)
    }

    it("should calculate the average value of transactions per account") {
      transactionAnalyser.calcAvgPerAccount() shouldBe
        Map(
          "A47" -> Map("FF" -> 900.67),
          "A32" -> Map("EE" -> 877.0),
          "A5" -> Map("BB" -> 677.89),
          "A27" -> Map("GG" -> 338.11),
          "A19" -> Map("GG" -> 612.83),
          "A45" -> Map("BB" -> 695.21),
          "A17" -> Map("BB" -> 533.15),
          "A40" -> Map("DD" -> 633.84),
          "A42" -> Map("FF" -> 174.04),
          "A29" -> Map("FF" -> 827.53),
          "A18" -> Map("EE" -> 718.32),
          "A44" -> Map("AA" -> 501.71)
        )
    }

    it("should ") {

      val transactions = Seq(
        Transaction("T0001", "A27", 1, "GG", 338.11),
        Transaction("T0002", "A5", 1, "BB", 677.89),
        Transaction("T00070", "A40", 2, "DD", 633.84),
        Transaction("T00071", "A17", 2, "BB", 533.15),
        Transaction("T000109", "A45", 3, "BB", 695.21),
        Transaction("T000110", "A44", 3, "AA", 501.71),
        Transaction("T000135", "A29", 4, "FF", 827.53),
        Transaction("T000136", "A18", 4, "EE", 718.32),
        Transaction("T000200", "A42", 5, "FF", 174.04),
        Transaction("T000201", "A47", 5, "FF", 900.67),
        Transaction("T000211", "A32", 6, "EE", 877.00),
        Transaction("T000212", "A19", 6, "GG", 612.83)
      )
      transactionAnalyser.calcRollingWindowStats() shouldBe
        List(
          (6,"A47",900.67,900.67,0.0,0.0,900.67),
          (6,"A5",677.89,677.89,0.0,0.0,0.0),
          (6,"A27",338.11,338.11,0.0,0.0,0.0),
          (6,"A45",695.21,695.21,0.0,0.0,0.0),
          (6,"A17",533.15,533.15,0.0,0.0,0.0),
          (6,"A40",633.84,633.84,0.0,0.0,0.0),
          (6,"A42",174.04,174.04,0.0,0.0,174.04),
          (6,"A29",827.53,827.53,0.0,0.0,827.53),
          (6,"A18",718.32,718.32,0.0,0.0,0.0),
          (6,"A44",501.71,501.71,501.71,0.0,0.0))
    }
  }

}
