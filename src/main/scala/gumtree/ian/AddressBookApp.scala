package gumtree.ian

import java.time.LocalDate

import gumtree.ian.domain.{Gender, Male}
import gumtree.ian.parser.{AddressBookReader, ClasspathAddressBookReader, CsvParser}
import java.time.temporal.ChronoUnit.DAYS

class AddressBookApp(filename: String) { self: AddressBookReader =>
  private val addressBook = readFile(filename)

  def howMany(gender: Gender): Int = {
    addressBook.count(_.gender == gender)
  }

  def whoIsOldest: Option[String] = {
    implicit val ordering: Ordering[LocalDate] = Ordering.fromLessThan(_ isBefore _)
    addressBook match {
      case _ if addressBook.length > 0 => Some(addressBook.minBy(_.dob).name)
      case _ => None
    }
  }

  def ageDifference(firstPerson: String, secondPerson: String): Long = {
    // this could be optimised if lots of data
    val startDate: Option[LocalDate] = addressBook.find(_.name == firstPerson).map(entry => {entry.dob})
    val endDate: Option[LocalDate] = addressBook.find(_.name == secondPerson).map(entry => {entry.dob})
    (startDate, endDate) match {
      case (Some(start), Some(end)) => DAYS.between(start, end)
      case _ => throw new InputException
    }
  }
}

object CsvAddressBookApp extends App {
  // add normal argument validation here
  val addressBookApp = new AddressBookApp("AddressBook") with ClasspathAddressBookReader with CsvParser

  println(s"There are ${addressBookApp.howMany(Male)} males in the address book")
  println(s"${addressBookApp.whoIsOldest.get} is the oldest person in the address book")
  println(s"Bill is ${addressBookApp.ageDifference("Bill McKnight","Paul Robinson")} than Paul")
}
