package gumtree.ian

import gumtree.ian.domain.{Gender, Male}
import gumtree.ian.parser.{AddressBookReader, ClasspathAddressBookReader, CsvParser}

class AddressBookApp(filename: String) { self: AddressBookReader =>
  val addressBook = readFile(filename)

  def howMany(gender: Gender): Int = {
    addressBook.count(_.gender == gender)
  }
}

object CsvAddressBookApp extends App {
  // add normal argument validation here
  val addressBookApp = new AddressBookApp("AddressBook") with ClasspathAddressBookReader with CsvParser

  println(s"There are ${addressBookApp.howMany(Male)} males in the address book")
}
