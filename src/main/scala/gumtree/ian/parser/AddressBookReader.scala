package gumtree.ian.parser

import gumtree.ian.domain.AddressEntry

import scala.io.Source

/*
  Classpath specific version for reading file from on classpath
  Built with parser providing the parseLine function
 */
trait ClasspathAddressBookReader extends AddressBookReader { self: AddressBookParser =>

  override def readFile(fileName: String): Seq[AddressEntry] = {
    val stream = getClass.getResourceAsStream(s"/$fileName")
    Option(stream).isDefined match {
      case true => Source.fromInputStream (stream).getLines map {
        line: String =>
        parseLine (line)
      } toSeq
      case false => throw new ParsingException("File not found")
    }
  }
}

/*
  Generic trait for reading file
 */
trait AddressBookReader {
  def readFile(filename: String): Seq[AddressEntry]
}