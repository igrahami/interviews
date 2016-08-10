import cucumber.api.CucumberOptions
import cucumber.api.junit.Cucumber
import org.junit.runner.RunWith

@RunWith(classOf[Cucumber])
@CucumberOptions(
  tags = Array("@wip"),
  plugin = Array("pretty", "html:target/cucumber/html"))
class RunWipTests