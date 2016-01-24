package io.niedzwiedz.belfort.parsing

import org.jsoup.Jsoup
import org.jsoup.nodes.{Document, Element}

object AuctionParser {
  def parse(content: String): Int = {
    val doc = Jsoup.parse(content)


    def parseAuctionId(doc: Document): (String, Option[String]) = {
      val AuctionIDPattern = """\d+""".r
      val auctionId = AuctionIDPattern.findFirstIn(doc.select("span.auctionID").html())
      ("auctionID", auctionId)
    }

    def parseDebtParams(doc: Document) = {
      for (elem <- doc.select("#t_dane tr").toArray()) {
        val element = elem.asInstanceOf[Element]
        val name = element.select("th").first().html().stripSuffix(":")
        val value =element.select("td strong").first().html()
        println(name, value)
      }
    }
    def parseBorrowerParams(doc: Document) = {
      for (elem <- doc.select("#dane_pozyczkobiorcy tr").toArray()) {
        val element = elem.asInstanceOf[Element]
        val name = element.select("th").first().html().stripSuffix(":")

        // It can be empty
        val value = element.select("td").first().html()
        println(name, value)
      }
    }
    parseDebtParams(doc)
    parseBorrowerParams(doc)
    content.length()
  }

}
