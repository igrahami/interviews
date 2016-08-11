package gumtree.ian.parser

import java.time.format.DateTimeFormatter
import gumtree.ian.domain.AddressEntry

/*
  generic parser for turning string line into an AddressEntry object
 */
trait AddressBookParser {
  val dateFormatter: DateTimeFormatter
  def parseLine(line: String): AddressEntry
}