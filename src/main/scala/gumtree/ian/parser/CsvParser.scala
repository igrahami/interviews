package gumtree.ian.parser

import java.time.LocalDate
import java.time.format.{DateTimeFormatter, DateTimeParseException}

import gumtree.ian.domain.{AddressEntry, Gender}

trait CsvParser extends AddressBookParser {
  override lazy val dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yy")

  override def parseLine(line: String): AddressEntry = {
    line.split(",") match {
      case Array(name, gender, dob) => {
        AddressEntry(name.trim, Gender(gender.trim), parseDate(dob.trim))
      }
      case _ => throw new ParsingException(s"$line incorrectly formatted")
    }
  }

  private def parseDate(dob: String): LocalDate = {
    try {
      LocalDate.parse(dob, dateFormatter)
    } catch {
      case e: DateTimeParseException => throw new ParsingException("DOB in an incorrect format, must be dd/MM/yy")
    }
  }
}