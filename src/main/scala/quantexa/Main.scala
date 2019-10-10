package quantexa

object Main extends App {

//  val transactionAnalyser = TransactionAnalyser(args(0))
//
//  println("total transaction value for all transactions for each day:")
//  transactionAnalyser.calcTotalPerDay()
//    .foreach(t => println(s"Transaction day: ${t._1}, Total amount: ${t._2}"))
//
//  println("Average value of transactions per account:")
//  transactionAnalyser.calcAvgPerAccount()
//    .foreach(t => println(s"Account: ${t._1}, average value per transaction type: ${t._2.values.mkString(" ")}"))
//
//  println("Rolling window statistics:")
//  transactionAnalyser.calcRollingWindowStats()
//    .foreach(t => println(t.productIterator.toSeq.mkString(" ")))
//
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
  println(transactionAnalyser.calcRollingWindowStats().toString())


}
