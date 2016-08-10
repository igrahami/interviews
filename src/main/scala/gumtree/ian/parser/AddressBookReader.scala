package gumtree.ian.parser

import gumtree.ian.domain.AddressEntry

import scala.io.Source

trait ClasspathAddressBookReader extends AddressBookReader { self: AddressBookParser =>

  override def readFile(fileName: String): Seq[AddressEntry] = {
    val stream = getClass.getResourceAsStream(s"/$fileName")
    Source.fromInputStream(stream).getLines map { line: String =>
      parseLine(line)
    } toSeq
  }
}

trait AddressBookReader {
  def readFile(filename: String): Seq[AddressEntry]
}