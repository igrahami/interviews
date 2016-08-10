package gumtree.ian.parser

import java.time.LocalDate
import java.time.format.DateTimeFormatter

import gumtree.ian.domain.{AddressEntry, Male}
import org.scalatest.{FlatSpec, Matchers}

class CsvParserTest extends FlatSpec with Matchers {

  it should "correctly parse a string into an AddressEntry" in new CsvParser {
    parseLine("Bob,Male,16/03/77") shouldBe AddressEntry("Bob", Male, LocalDate.parse("16/03/77", DateTimeFormatter.ofPattern("dd/MM/yy")))
  }

  it should "correctly parse a string into an AddressEntry ignoring padding" in new CsvParser {
    parseLine("Bob,   Male  ,  16/03/77  ") shouldBe AddressEntry("Bob", Male, LocalDate.parse("16/03/77", DateTimeFormatter.ofPattern("dd/MM/yy")))
  }

  it should "fail with parsing exception when line is missing a field" in new CsvParser {
    intercept[ParsingException] {
      parseLine("Missing Gender, 16/03/77")
    }
  }

  it should "fail with parsing exception when line has too many fields" in new CsvParser {
    intercept[ParsingException] {
      parseLine("Name, Male, 16/03/77, Something else")
    }
  }

  it should "fail with parsing exception when gender not correct" in new CsvParser {
    intercept[ParsingException] {
      parseLine("Invalid Gender, Not Quite Sure, 16/03/77")
    }
  }

  it should "fail with parsing exception when dob invalid format" in new CsvParser {
    intercept[ParsingException] {
      parseLine("Name, Female, invalid")
    }
  }
}
