Feature: Address-book

  Background:
    Given I have the application running with the default address book

  @wip
  Scenario: How many males are in the address book
    When I ask for the number of males
    Then the result is 3

  @todo
  Scenario: Who is the oldest person in the address book
    When I ask for who the oldest person is
    Then the result is Wes Jackson

  @todo
  Scenario: How many days older is Bill than Paul
    When I ask how many days older Bill is than Paul
    Then the result is 2862