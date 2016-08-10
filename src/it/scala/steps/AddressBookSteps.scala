package steps

import cucumber.api.PendingException
import cucumber.api.scala.{EN, ScalaDsl}

class AddressBookSteps extends ScalaDsl with EN {

  Given("""^I have the application running with the default address book$"""){ () =>
    throw new PendingException()
  }

  When("""^I ask for the number of males$"""){ () =>
    throw new PendingException()
  }

  Then("""^the result is (\d+)$"""){ (arg0:Int) =>
    throw new PendingException()
  }

  Then("""^the result is (\c+)$"""){ (arg0:String) =>
    throw new PendingException()
  }

}
