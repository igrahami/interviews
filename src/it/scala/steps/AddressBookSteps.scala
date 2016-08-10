package steps

import cucumber.api.scala.{EN, ScalaDsl}
import gumtree.ian.AddressBookApp
import gumtree.ian.domain.Gender
import gumtree.ian.parser.{ClasspathAddressBookReader, CsvParser}
import org.scalatest.Matchers

class AddressBookSteps extends ScalaDsl with EN with Matchers {

  lazy val addressBook: AddressBookApp = new AddressBookApp("AddressBook") with ClasspathAddressBookReader with CsvParser
  var intResult: Option[Int] = None
  var stringResult: Option[String] = None

  Given("""^I have the application running with the default address book$"""){ () =>
    // just initialize the variable
    addressBook
  }

  When("""^I ask for the number of (Male|Female)$"""){ (gender: String) =>
    intResult = Some(addressBook.howMany(Gender(gender)))
  }

  When("""^I ask for who the oldest person is$"""){ () =>
    stringResult = addressBook.whoIsOldest
  }

  When("""^I ask how many days older (.*) is than (.*)$"""){ (firstPerson: String, secondPerson: String) =>
    intResult = Some(addressBook.ageDifference(firstPerson, secondPerson).toInt)
  }

  Then("""^the result is (\d+)$"""){ (result:Int) =>
    intResult shouldBe Some(result)
  }

  Then("""^the result is ([a-zA-Z ]+)$"""){ (result:String) =>
    stringResult shouldBe Some(result)
  }

}
