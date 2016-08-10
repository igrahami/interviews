package gumtree.ian.parser

class ParsingException(message: String) extends RuntimeException {
  override def toString = message
}