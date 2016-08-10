package gumtree.ian

import java.time.LocalDate

import gumtree.ian.domain.{AddressEntry, Female, Male}
import gumtree.ian.parser.AddressBookReader
import org.scalatest.{FlatSpec, Matchers}

class AddressBookAppTest extends FlatSpec with Matchers {

  it should "return correct number of males when only males" in new AddressBookApp("filename") with AddressBookReader {
    override def readFile(filename: String): Seq[AddressEntry] = Seq(AddressEntry("Bob", Male, LocalDate.now()))

    howMany(Male) shouldBe 1
  }

  it should "return zero when no males" in new AddressBookApp("filename") with AddressBookReader {
    override def readFile(filename: String): Seq[AddressEntry] = Seq(AddressEntry("Sam", Female, LocalDate.now()))

    howMany(Male) shouldBe 0
  }

  it should "return correct number when mixed genders"  in new AddressBookApp("filename") with AddressBookReader {
    override def readFile(filename: String): Seq[AddressEntry] = Seq(AddressEntry("Sam", Male, LocalDate.now()), AddressEntry("Sam", Female, LocalDate.now()), AddressEntry("Sam", Male, LocalDate.now()))

    howMany(Male) shouldBe 2
  }
}
