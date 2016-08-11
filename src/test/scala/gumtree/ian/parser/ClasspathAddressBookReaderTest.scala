package gumtree.ian.parser

import java.time.LocalDate
import java.time.format.DateTimeFormatter

import gumtree.ian.domain.{Male, AddressEntry}
import org.scalatest.{Matchers, FlatSpec}

class ClasspathAddressBookReaderTest extends FlatSpec with Matchers {

  private val dateNow = LocalDate.now()

  trait AddressBookParserStub extends AddressBookParser {
    override lazy val dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yy")

    override def parseLine(line: String): AddressEntry = {
      AddressEntry("STUB", Male, dateNow)
    }
  }

  it should "read the lines in a file and try to parse them" in new ClasspathAddressBookReader with AddressBookParserStub {
    val result = readFile("testAddressBook10Lines")
    result.length shouldBe 10
  }

  it should "process an empty file to an empty sequence" in new ClasspathAddressBookReader with AddressBookParserStub {
    val result = readFile("testAddressBookNoLines")
    result.length shouldBe 0
  }

  it should "throw exception on file not found" in new ClasspathAddressBookReader with AddressBookParserStub {
    intercept[ParsingException] {
      readFile("fileDoesntExist")
    }
  }
}