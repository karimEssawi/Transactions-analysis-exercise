package quantexa

import scala.collection.immutable.TreeMap
import scala.io.Source

case class Transaction(transactionId: String, accountId: String, transactionDay: Int, category: String, transactionAmount: Double)

class TransactionAnalyser(transactions: Seq[Transaction]) {

  def calcTotalPerDay(): Map[Int, Double] = {
    TreeMap(transactions
      .groupBy(_.transactionDay).toSeq: _*)
      .mapValues(_.map(_.transactionAmount).sum)
  }

  def calcAvgPerAccount(): Map[String, Map[String, Double]] = {
    transactions
      .groupBy(_.accountId)
      .mapValues {
        _.groupBy(_.category).mapValues(t => t.map(_.transactionAmount).sum / t.length)
      }
  }

  def calcRollingWindowStats(): Seq[(Int, String, Double, Double, Double, Double, Double)] = {
    def calcStats(day: Int) = {
      val rollingWindow = transactions
        .filter(t => t.transactionDay >= day - 5 && t.transactionDay < day)
        .groupBy(_.accountId)

      val maxTransaction = rollingWindow.mapValues(_.map(_.transactionAmount).max)
      val avgTransaction = rollingWindow.mapValues(t => t.map(_.transactionAmount).sum / t.length)

      def getTotalForCategory(category: String): Map[String, Double] = {
        rollingWindow.mapValues(_.filter(_.category == category))
          .mapValues(_.map(_.transactionAmount).sum)
      }

      val aaTotal = getTotalForCategory("AA")
      val ccTotal = getTotalForCategory("CC")
      val ffTotal = getTotalForCategory("FF")

      rollingWindow.map { case (id, _) =>
        (day, id, maxTransaction.getOrElse(id, 0.0), avgTransaction.getOrElse(id, 0.0), aaTotal.getOrElse(id, 0.0),
          ccTotal.getOrElse(id, 0.0), ffTotal.getOrElse(id, 0.0))
      }.toSeq
    }

    transactions.map(_.transactionDay).distinct.sorted.drop(5).flatMap(calcStats)
  }
}

object TransactionAnalyser {
  def apply(filePath: String): TransactionAnalyser = {
    val transactionsLines = Source.fromFile(filePath).getLines().drop(1)
    val transactions = transactionsLines.map { line =>
      val split = line.split(',')
      Transaction(split(0), split(1), split(2).toInt, split(3), split(4).toDouble)
    }.toList
    new TransactionAnalyser(transactions)
  }
}

