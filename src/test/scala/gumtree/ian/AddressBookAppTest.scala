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

  it should "return zero number of males when no entries" in new AddressBookApp("filename") with AddressBookReader {
    override def readFile(filename: String): Seq[AddressEntry] = Seq()

    howMany(Male) shouldBe 0
  }

  it should "return zero when no males" in new AddressBookApp("filename") with AddressBookReader {
    override def readFile(filename: String): Seq[AddressEntry] = Seq(AddressEntry("Sam", Female, LocalDate.now()))

    howMany(Male) shouldBe 0
  }

  it should "return correct number when mixed genders" in new AddressBookApp("filename") with AddressBookReader {
    override def readFile(filename: String): Seq[AddressEntry] = Seq(AddressEntry("Sam", Male, LocalDate.now()), AddressEntry("Sam", Female, LocalDate.now()), AddressEntry("Sam", Male, LocalDate.now()))

    howMany(Male) shouldBe 2
  }

  it should "return Bob when he is the oldest" in new AddressBookApp("filename") with AddressBookReader {
    override def readFile(filename: String): Seq[AddressEntry] = Seq(AddressEntry("Bob", Male, LocalDate.now().minusYears(100)), AddressEntry("Sam", Female, LocalDate.now().minusYears(20)), AddressEntry("Sam", Male, LocalDate.now()))

    whoIsOldest shouldBe Some("Bob")
  }

  it should "return Bob when he is joint oldest but first in list" in new AddressBookApp("filename") with AddressBookReader {
    override def readFile(filename: String): Seq[AddressEntry] = {
      val now: LocalDate = LocalDate.now()
      Seq(AddressEntry("Bob", Male, now.minusYears(100)), AddressEntry("Sam", Female, now.minusYears(100)), AddressEntry("Sam", Male, now))
    }

    whoIsOldest shouldBe Some("Bob")
  }

  it should "return none when empty list" in new AddressBookApp("filename") with AddressBookReader {
    override def readFile(filename: String): Seq[AddressEntry] = Seq()

    whoIsOldest shouldBe None
  }

  it should "return the age difference" in new AddressBookApp("filename") with AddressBookReader {
    override def readFile(filename: String): Seq[AddressEntry] = {
      val now: LocalDate = LocalDate.now()
      Seq(AddressEntry("Bob", Male, now.minusYears(1).minusDays(20)), AddressEntry("Sam", Female, now))
    }

    ageDifference("Bob", "Sam") shouldBe 386
  }

  it should "return the age difference negative when first younger" in new AddressBookApp("filename") with AddressBookReader {
    override def readFile(filename: String): Seq[AddressEntry] = {
      val now: LocalDate = LocalDate.now()
      Seq(AddressEntry("Bob", Male, now.minusYears(1).minusDays(20)), AddressEntry("Sam", Female, now))
    }

    ageDifference("Sam", "Bob") shouldBe -386
  }

  it should "throw input exception when name doesn't exist in address book" in new AddressBookApp("filename") with AddressBookReader {
    override def readFile(filename: String): Seq[AddressEntry] = {
      val now: LocalDate = LocalDate.now()
      Seq(AddressEntry("Bob", Male, now.minusYears(1).minusDays(20)), AddressEntry("Sam", Female, now))
    }

    intercept[InputException] {
      ageDifference("Bob", "Marley")
    }
  }
}
