package gumtree.ian.domain

import gumtree.ian.parser.ParsingException

sealed class Gender
case object Male extends Gender
case object Female extends Gender

object Gender {
  def apply(input: String): Gender = {
    input match {
      case "Male" => Male
      case "Female" => Female
      case _ => throw new ParsingException("Incorrect gender")
    }
  }
}

case class AddressEntry(name: String, gender: Gender, dob: java.time.LocalDate)
