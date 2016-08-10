package steps

import cucumber.api.PendingException
import cucumber.api.scala.{EN, ScalaDsl}
import gumtree.ian.AddressBookApp
import gumtree.ian.domain.Gender
import gumtree.ian.parser.{ClasspathAddressBookReader, CsvParser}
import org.scalatest.Matchers

class AddressBookSteps extends ScalaDsl with EN with Matchers {

  lazy val addressBook: AddressBookApp = new AddressBookApp("AddressBook") with ClasspathAddressBookReader with CsvParser
  var intResult: Option[Int] = None

  Given("""^I have the application running with the default address book$"""){ () =>
    // just initialize the variable
    addressBook.addressBook
  }

  When("""^I ask for the number of (Male|Female)$"""){ (gender: String) =>
    intResult = Some(addressBook.howMany(Gender(gender)))
  }

  Then("""^the result is (\d+)$"""){ (result:Int) =>
    intResult shouldBe Some(result)
  }

  Then("""^the result is (\c+)$"""){ (arg0:String) =>
    throw new PendingException()
  }

}
