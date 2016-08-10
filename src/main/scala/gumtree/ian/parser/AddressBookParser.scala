package gumtree.ian.parser

import java.time.format.DateTimeFormatter
import gumtree.ian.domain.AddressEntry

trait AddressBookParser {
  val dateFormatter: DateTimeFormatter
  def parseLine(line: String): AddressEntry
}