package quantexa

object Main extends App {

  val transactionAnalyser = TransactionAnalyser(args(0))

  println("total transaction value for all transactions for each day:")
  transactionAnalyser.calcTotalPerDay()
    .foreach(t => println(s"Transaction day: ${t._1}, Total amount: ${t._2}"))

  println("Average value of transactions per account:")
  transactionAnalyser.calcAvgPerAccount()
    .foreach(t => println(s"Account: ${t._1}, average value per transaction type: ${t._2.values.mkString(" ")}"))

  println("Rolling window statistics:")
  transactionAnalyser.calcRollingWindowStats()
    .foreach(t => println(t.productIterator.toSeq.mkString(" ")))
}
