package io.niedzwiedz.belfort.parsing

import org.scalatest._

/**
  * Created by dzwiedziu on 23/01/16.
  */
class AuctionParser$Test extends FlatSpec with Matchers {
  "AuctionParser" should "work (it is just a test of test framework" in {
    val x = scala.io.Source.fromURL(getClass.getResource("/HTMLDump/Auction/17123.html")).mkString
    AuctionParser.parse(x)
    AuctionParser.parse("Four") should be (4)
  }
}
